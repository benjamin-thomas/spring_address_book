<#macro main title="Layout example">

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>${title}</title>

        <link rel="stylesheet" href="/main.css">
    </head>

    <body>
    <#include "components/header.ftlh"/>



    <#nested/>

    <#include "components/footer.ftlh"/>

    </body>
    </html>

</#macro>