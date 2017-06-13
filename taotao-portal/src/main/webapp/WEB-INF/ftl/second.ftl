<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${title!"标题"}</title>

</head>
<body>
<label>学号：</label>${student.id}<br>
<label>姓名：</label>${student.name}<br>
<label>住址：</label>${student.address}<br>

<table border="1">
<#list students as s>
    <#if s_index%2==0>
        <tr style="color: chartreuse">
    <#else >
        <tr>
    </#if>
        <td>${s_index}</td>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.address}</td>
    </tr>
</#list>
</table>
<#--当前时间:${cur_date?string("yyyy/MM/dd HH:mm:ss")}<br>-->
<#--当前时间:${date()}-->
<#--当前时间:${cur_time?string("yyyy/MM/dd HH:mm:ss")}-->
<#--${dateVar?string("yyyy-MM-dd HH:mm:ss zzzz")}-->
<#--${openingTime?string.short}-->
Today is ${.now?date}<br>
<#if cur_date ??>
当前时间:${cur_date?string("yyyy/MM/dd HH:mm:ss")}<br>
<#else >
cur_time为空
</#if>
</body>
</html>