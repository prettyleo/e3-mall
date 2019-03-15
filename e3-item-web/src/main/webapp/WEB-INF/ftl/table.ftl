<html>
<head>
    <title>FreeMarker</title>
</head>
<body>
    <p>当前日期:${date?string("yyyy/MM/dd HH:mm")}</p>
    <p>空值处理:<#if empty??>${empty}<#else >值居然是空的</#if>${empty!"空值"}</p>
    <#include "hell.ftl">
    <table>
        <tr>
            <th>
                下标
            </th>
            <th>
                学生姓名
            </th>
            <th>
                学号
            </th>
            <th>
                班级
            </th>
            <th>
                成绩
            </th>
        </tr>
        <#list studentList as student>
        <#if student_index%2==0>
            <tr bgcolor="red">
        <#else >
            <tr bgcolor="aqua">
        </#if>
                <td>${student_index}</td>
                <td>${student.name}</td>
                <td>${student.num}</td>
                <td>${student.clazz}</td>
                <td>${student.score}</td>
            </tr>
        </#list>
    </table>
</body>
</html>