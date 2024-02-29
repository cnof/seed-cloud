#!/bin/bash

WORKSPACE=$(cd "$(dirname "$0")"/ || exit; pwd)
cd "$WORKSPACE" || exit

mkdir -p var

module="${project.artifactId}"
app=ep-s-"$module"-${version}.jar
# 环境
env=-Dspring.profiles.active="${environment}"

# 配置文件地址
conf="application-${environment}.yml"

function check_pid() {
    running=$(pgrep -f "$app" | grep -cv "PID TTY")
    return "$running"
}

function start() {
    check_pid
    running=$?
    if [ $running -gt 0 ];then
        echo -n "$app now is running already"
        return 1
    fi

    if ! [ -f "$conf" ];then
        echo "Config file $conf doesn't exist, using default one."
    else
        source /etc/profile
        export BUILD_ID=dontKillMe
        nohup java -jar -Xmx2048m "${env}" -Dspring.config.location="${conf}" "${app}" > /dev/null 2>&1 &
    fi

    sleep 3
    running=$(pgrep -f "$app" | grep -cv "PID TTY")
    if [ "$running" -gt 0 ];then
        echo "$app started..."
    else
        echo "$app failed to start."
        return 1
    fi
}

function stop() {
    pgrep -f "$app" | xargs kill -9
    echo "$app stopped..."
}

function restart() {
    stop
    sleep 1
    start
}

function status() {
    check_pid
    running=$?
    if [ $running -gt 0 ];then
        echo started
    else
        echo stoped
    fi
}

function help() {
    echo "$0 start|stop|restart|status"
}

if [ "$1" == "" ]; then
    help
elif [ "$1" == "stop" ];then
    stop
elif [ "$1" == "start" ];then
    start
elif [ "$1" == "restart" ];then
    restart
elif [ "$1" == "status" ];then
    status
else
    help
fi
