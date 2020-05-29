<#import "../layouts/main.ftlh" as layout>

<@layout.main title="New city">

    <h1>New city form</h1>

    <h2>TODO: CSRF token</h2>

<#--
    Customize form controls:
        https://blog.trifork.com/2013/05/27/bootstrap-spring-mvc-form-input-freemarker-macros/
-->
    <#import "/spring.ftl" as spring/>
    <@spring.bind "cityForm" /><#-- param name must match validated class name -->

    <#if (spring.status.errors.allErrors?size > 0) >
        <h2>Global errors</h2>

        <#list spring.status.errors.allErrors as error>
            <span class="has-error">${error.defaultMessage} </span>
        </#list>

        <hr>
    </#if>

    <form action="/freemarker/city" method="post">
        Name:<br>
        <#--
        <input type="text" value="${(city.name)!''}"><br>
        -->
        <@spring.formInput "cityForm.name" />
        <@spring.showErrors "<br>", 'error' />
        <br>


        Zip code:<br>
        <@spring.formInput "cityForm.zipCode" />
        <@spring.showErrors "<br>", 'error' />
        <br>

        <input type="submit">

    </form>

</@layout.main>