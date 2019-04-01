<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
    <thead>
    <tr>
        <th scope="col" colspan="4" class="text-center">Редактирование пользователя ID:"${user.id}"</th>
    </tr>
    </thead>
    </table>

    <form action="/user" method="post">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Логин</th>
                <th scope="col">Пароль</th>
                <th scope="col">USER</th>
                <th scope="col">ADMIN</th>
            </tr>
            </thead>
            <tr>
            <th scope="col"><input type="text" name="username" value="${user.username}"></th>
            <th scope="col"><input type="text" name="password" value="${user.password}"></th>
        <#list roles as role>
                <th scope="col"><lable><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</lable>
                </th>
        </#list>
            </tr>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        </table>
        <button type="submit">Сохранить</button>
    </form>
</@c.page>