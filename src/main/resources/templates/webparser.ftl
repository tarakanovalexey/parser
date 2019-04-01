<#import "parts/common.ftl" as c>
<@c.page>
    <div id="carouselExampleSlidesOnly" style="text-align: center; width: 100px; margin: 0 auto" class="carousel slide carousel-fade" data-ride="carousel" data-pause="false">
        <div class="carousel-inner" style="text-align: center;">
            <div class="carousel-item active">
                <img width="100" height="100" src="https://tech.hh.ru/api/logos/min-hh-red.png" alt="hh">
            </div>
            <div class="carousel-item">
                <img width="100" height="100" src="https://lh3.googleusercontent.com/_EzIFvnN9Yd9tRwMcwM7G2RCq4l2R4fGnXByeYMW4Mroiju71-vGjtdElZkvwHYFltU=s180"  alt="superjob">
            </div>
            <div class="carousel-item">
                <img width="100" height="100" src="http://avito-club.ru/wp-content/uploads/2017/08/cropped-Logo-Avito.png" alt="avito">
            </div>
        </div>
    </div>
    <br>
    <br>
    <form action="/parseit" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <table class="table">
        <thead>
        <tr>
            <th scope="col" colspan="2" class="text-center">Парсер резюме</th>
        </tr>
        </thead>
        <thead class="thead-light">
        <tr>
            <th scope="col" colspan="2" class="text-center">Возраст</th>
        </tr>
        </thead>
        <tr>
            <th scope="col"><input class="form-control" type="text" maxlength="2" name="ageFrom" placeholder="Возраст от" /></th>
            <th scope="col"><input class="form-control" type="text" maxlength="2" name="ageTo" placeholder="Возраст до" /></th>
        </tr>
        <thead class="thead-light">
        <tr>
            <th scope="col" colspan="2" class="text-center">Зарплата (руб.)</th>
        </tr>
        </thead>
        <tr>
            <th scope="col"><input class="form-control" type="text" maxlength="7" name="salaryFrom" placeholder="Зарплата от" /></th>
            <th scope="col"><input class="form-control" type="text" maxlength="8" name="salaryTo" placeholder="Зарплата до" /></th>
        </tr>
        <thead class="thead-light">
        <tr>
            <th scope="col" colspan="2" class="text-center">Регион</th>
        </tr>
        </thead>
        <tr>
        <th scope="col" colspan="2">
            <select class="custom-select" name="city">
            <option selected value="1">Москва</option>
            <option value="2">Санкт-Петербург</option>
            <option value="53">Краснодар</option>
            </select>
        </th>
        </tr>
        <tr>
            <th scope="col" colspan="2" class="text-center"><button type="submit" class="btn btn-outline-primary" style="width: 8rem;">Подтвердить</button></th>
        </tr>
    </table>

    </form>
    <div class="card-columns">
    <#list urls as message>
        <div class="card my-3">
            <div class="card-header">
                <span>Запрос ${message.getDate()?string('dd.MM.yyyy HH:mm:ss')}</span>
            </div>
            <div class="m-2">
            <span>HH.ru: ${message.getFoundResumes()}</span>
                <div class="text-muted" style="font-size: 10px;">
                Возраст
                <span> от ${message.getAgeFrom()}</span>
                <span> до ${message.getAgeTo()}</span>
                <br>
                Зарплата
                <span> от ${message.getSalaryFrom()}</span>
                <span> до ${message.getSalaryTo()}</span>
                <br>
                Регион
                <span> ${message.getCity()}</span>
                </div>
            </div>
        <div class="card-footer text-muted">
            <form method="post" action="/saveexcel">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="thisid" value="${message.getId()}"/>
                <button class="btn btn-outline-success m-2" style="width: 8rem;">Excel</button>
            </form>
            <form method="post" action="/delete">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="thisid" value="${message.getId()}"/>
                <button class="btn btn-outline-danger m-2" type="submit" style="width: 8rem;">Удалить</button>
            </form>
        </div>
        </div>
    <#else>
    <div class="m-2">
        У вас нет истории запросов
    </div>
    </#list>
    </div>
</@c.page>