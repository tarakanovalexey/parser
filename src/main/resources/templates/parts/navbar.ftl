<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Главная</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if name = "Гость">
            <li class="nav-item">
                <a class="nav-link" href="/login">Авторизация</a>
            </li>
            </#if>
            <#if name != "Гость">
            <li class="nav-item">
                <a class="nav-link" href="/main">Парсер</a>
            </li>
            </#if>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            </#if>

        </ul>

        <div class="navbar-text mr-3">
            ${name}
        </div>
            <#if name != "Гость">
                    <@l.logout />
            </#if>
    </div>
</nav>