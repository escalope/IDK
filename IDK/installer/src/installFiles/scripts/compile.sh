#!/bin/sh

cd ../..

java -cp installFiles/lib/ant-antlr.jar:installFiles/lib/ant-apache-resolver.jar:installFiles/lib/ant-jdepend.jar:installFiles/lib/ant-nodeps.jar:installFiles/lib/ant-weblogic.jar:installFiles/lib/ant-apache-bcel.jar:installFiles/lib/ant-commons-logging.jar:installFiles/lib/ant-jmf.jar:installFiles/lib/ant-starteam.jar:installFiles/lib/xercesImpl.jar:installFiles/lib/ant-apache-bsf.jar:installFiles/lib/ant-commons-net.jar:installFiles/lib/ant-jsch.jar:installFiles/lib/ant-stylebook.jar:installFiles/lib/xml-apis.jar:installFiles/lib/ant-apache-log4j.jar:installFiles/lib/ant-jai.jar:installFiles/lib/ant-junit.jar:installFiles/lib/ant-swing.jar:installFiles/lib/ant-apache-oro.jar:installFiles/lib/ant.jar:installFiles/lib/ant-launcher.jar:installFiles/lib/ant-testutil.jar:installFiles/lib/ant-apache-regexp.jar:installFiles/lib/ant-javamail.jar:installFiles/lib/ant-netrexx.jar:installFiles/lib/ant-trax.jar:installFiles/lib/ant-launcher.jar org.apache.tools.ant.launch.AntMain compile modules

java -jar installFiles/lib/ant-launcher.jar org.apache.tools.ant.launch.AntMain compile modules