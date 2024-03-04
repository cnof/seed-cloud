package com.misssyc.seed.generator.utils;

import cn.hutool.core.text.CharSequenceUtil;
import com.misssyc.seed.common.core.constants.GenConstants;
import com.misssyc.seed.common.core.utils.StringUtils;
import com.misssyc.seed.generator.config.GenConfig;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

/**
 * 代码生成器 工具类
 * 
 * @author ruoyi
 */
public class GenUtils
{
    /**
     * 初始化表信息
     */
    public static void initTable(GenTable genTable, String operName, GenTableAddDTO param)
    {
        genTable.setClassName(convertClassName(genTable.getTableName(), param));
        genTable.setPackageName(param.getPackageName());
        genTable.setModuleName(getModuleName(param.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(param.getAuthor());
        genTable.setCreateBy(operName);
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(GenTableColumn column, GenTable table)
    {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(CharSequenceUtil.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(GenConstants.TYPE_STRING);
        column.setQueryType(GenConstants.QUERY_EQ);

        if (arraysContains(GenConstants.COLUMN_TYPE_STR, dataType) || arraysContains(GenConstants.COLUMN_TYPE_TEXT, dataType))
        {
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || arraysContains(GenConstants.COLUMN_TYPE_TEXT, dataType) ? GenConstants.HTML_TEXTAREA : GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }
        else if (arraysContains(GenConstants.COLUMN_TYPE_TIME, dataType))
        {
            column.setJavaType(GenConstants.TYPE_DATE);
            column.setHtmlType(GenConstants.HTML_DATETIME);
        }
        else if (arraysContains(GenConstants.COLUMN_TYPE_NUMBER, dataType))
        {
            column.setHtmlType(GenConstants.HTML_INPUT);

            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtils.splitToArray(StringUtils.subBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                column.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                column.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else
            {
                column.setJavaType(GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_EDIT, columnName) && StringUtils.isFalse(column.getIsPk()))
        {
            column.setIsEdit(GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_LIST, columnName) && StringUtils.isFalse(column.getIsPk()))
        {
            column.setIsList(GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(GenConstants.COLUMN_NAME_NOT_QUERY, columnName) && StringUtils.isFalse(column.getIsPk()))
        {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (StringUtils.endWithIgnoreCase(columnName, "name"))
        {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endWithIgnoreCase(columnName, "status"))
        {
            column.setHtmlType(GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endWithIgnoreCase(columnName, "type")
                || StringUtils.endWithIgnoreCase(columnName, "sex"))
        {
            column.setHtmlType(GenConstants.HTML_SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtils.endWithIgnoreCase(columnName, "image"))
        {
            column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
        }
        // 文件字段设置文件上传控件
        else if (StringUtils.endWithIgnoreCase(columnName, "file"))
        {
            column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (StringUtils.endWithIgnoreCase(columnName, "content"))
        {
            column.setHtmlType(GenConstants.HTML_EDITOR);
        }
    }

    /**
     * 校验数组是否包含指定值
     * 
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取模块名
     * 
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return StringUtils.sub(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 获取业务名
     * 
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName)
    {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return StringUtils.sub(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 表名转换成Java类名
     *
     * @param tableName 表名称
     * @param param
     * @return 类名
     */
    public static String convertClassName(String tableName, GenTableAddDTO param)
    {
        boolean autoRemovePre = param.isAutoRemovePre();
        String tablePrefix = param.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix))
        {
            String[] searchList = StringUtils.splitToArray(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return CharSequenceUtil.toCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     * 
     * @param replacement 替换值
     * @param searchList 替换列表
     */
    public static String replaceFirst(String replacement, String[] searchList)
    {
        String text = replacement;
        for (String searchString : searchList)
        {
            if (replacement.startsWith(searchString))
            {
                text = replacement.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 关键字替换
     * 
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text)
    {
        return RegExUtils.replaceAll(text, "(?:表|若依)", "");
    }

    /**
     * 获取数据库类型字段
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType)
    {
        if (StringUtils.indexOf(columnType, '(') > 0)
        {
            return StringUtils.subBefore(columnType, "(", false);
        }
        else
        {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType)
    {
        if (StringUtils.indexOf(columnType, '(') > 0)
        {
            String length = StringUtils.subBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }
        else
        {
            return 0;
        }
    }
}
