<?xml version="1.0"?>
<globals>
    <#include "root://activities/common/common_globals.xml.ftl" />
    <#assign useSupport=appCompat>
    <global id="ktOrJavaExt" type="string" value = "java" />
    <global id="useSupport" type="boolean" value="${useSupport?string}" />
    <global id="SupportPackage" value="${useSupport?string('.support.v4','')}" />
    <global id="resOut" value="${resDir}" />
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}" />
    <global id="superClass" type="string" value="MvvmFragment"/>
    <global id="superClassFqcn" type="string" value="io.auxo.arch.mvvm.view.fragment.MvvmFragment"/>
</globals>
