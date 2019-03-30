<#import "parts/common.ftl" as c>

<@c.page>

    <table class="table">
        <thead>
        <tr>
            <th scope="col" colspan="4" class="text-center">Список пользователей</th>
        </tr>
        </thead>
        <thead class="thead-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Логин</th>
            <th scope="col">Права</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td scope="row">${user.id}</td>
                <td scope="row">${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href ="/user/${user.id}">Изменить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>