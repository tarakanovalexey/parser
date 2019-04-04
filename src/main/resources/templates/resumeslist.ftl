<#import "parts/common.ftl" as c>
<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col" colspan="5" class="text-left">
                <form method="post" action="/excel">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <#list urls as url>
                    <input type="hidden" name="search_id" value="${url.getSearchId()}"/>
                    </#list>
                    <button class="btn btn-outline-success m-2" type="submit" style="width: 8rem;">Excel</button>
                </form>
            </th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th scope="col" colspan="5" class="text-center">Запрос</th>
        </tr>
        </thead>
        <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Должность</th>
            <th scope="col">Зарплата</th>
            <th scope="col">Возраст</th>
            <th scope="col">Опыт(мес.)</th>
        </tr>
        </thead>
        <tbody>
        <#assign count = 0>
        <#list urls as url>
            <tr>
                <#assign count = count + 1>
                <td scope="row">${count}</td>
                <td scope="row"><a href="${url.getLink()}"> ${url.getProfession()}</a></td>
                <td scope="row">${url.getPayment()}</td>
                <td scope="row">${url.getAge()}</td>
                <td scope="row">${url.getExperience()}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>