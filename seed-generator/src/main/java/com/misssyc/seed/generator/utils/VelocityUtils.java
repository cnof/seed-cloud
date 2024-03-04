package com.misssyc.seed.generator.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.misssyc.seed.common.core.constants.GenConstants;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author 33992
 * @since 2024/3/4
 **/
public class VelocityUtils {

    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable, List<GenTableColumn> columns) {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtil.now());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(columns));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", columns);
        velocityContext.put("table", genTable);

        return velocityContext;
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        return StringUtils.substring(packageName, 0, lastIndex);
    }

    /**
     * 根据列类型获取导入包
     *
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<GenTableColumn> columns) {
        HashSet<String> importList = new HashSet<>();

        for (GenTableColumn column : columns) {
            if (column.isSuperColumn()) {
                continue;
            }
            if (GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            }
            else if (GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName 模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName)
    {
        return CharSequenceUtil.format("{}:{}", moduleName, businessName);
    }

    /**
     * 获取模板信息
     * @param tplCategory 生成的模板
     * @param tplWebType 前端类型
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory, String tplWebType)
    {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/addOrUpdateVo.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/java/convert.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/po.java.vm");
        templates.add("vm/java/queryVo.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/vo.java.vm");
        templates.add("vm/xml/mapper.xml.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable)
    {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        String vuePath = "vue";

        if (template.contains("addOrUpdateVo.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/pojo/vo/{}.java", javaPath, className);
        }
        else if (template.contains("controller.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/controller/{}Controller.java", javaPath, className);
        }
        else if (template.contains("convert.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/mapstruct/{}Convert.java", javaPath, className);
        }
        else if (template.contains("mapper.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/mapper/{}Mapper.java", javaPath, className);
        }
        else if (template.contains("po.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/po/{}.java", javaPath, className);
        }
        else if (template.contains("queryVo.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/pojo/vo/{}QueryVO.java", javaPath, className);
        }
        else if (template.contains("service.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/service/{}Service.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
        }
        else if (template.contains("vo.java.vm"))
        {
            fileName = CharSequenceUtil.format("{}/pojo/vo/{}VO.java", javaPath, className);
        }
        else if (template.contains("mapper.xml.vm"))
        {
            fileName = CharSequenceUtil.format("{}/{}Mapper.xml", mybatisPath, className);
        }
        else if (template.contains("sql.vm"))
        {
            fileName = businessName + "Menu.sql";
        }
        else if (template.contains("api.js.vm"))
        {
            fileName = CharSequenceUtil.format("{}/api/{}/{}.js", vuePath, moduleName, businessName);
        }
        else if (template.contains("index.vue.vm"))
        {
            fileName = CharSequenceUtil.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
        }
        else if (template.contains("index-tree.vue.vm"))
        {
            fileName = CharSequenceUtil.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
        }
        return fileName;
    }
}
