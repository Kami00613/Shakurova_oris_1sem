
<html lang="en">

<#include "base.ftl">

<#macro title>
    Exception details
</#macro>

<#macro content>
    <h1>Exception details:</h1>
    <strong> Request uri: ${uri}</strong>
    <strong>Status code: ${statusCode}</strong>
    <#if message??><strong>${message}</strong></#if>
</#macro>

</html>
