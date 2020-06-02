<#macro main title="Layout example">

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>${title}</title>

        <link rel="stylesheet" href="/main.css">

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="/main.js"></script>
    </head>

    <body>
    <#include "components/header.ftlh"/>



    <#nested/>

    <#include "components/footer.ftlh"/>

    </body>
    </html>

</#macro>