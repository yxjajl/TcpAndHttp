package com.groovy

import com.util.StreamDrainer;

def cmd= /D:\Java\workspace_newcoresystem\portal-pc-newcoresystem\portal-online-pc\ecs-online-web-pc/

Process process = Runtime.getRuntime().exec("cmd /k mvn compile -f "+cmd+"\\pom.xml");
new Thread(new StreamDrainer(process.getInputStream())).start();
