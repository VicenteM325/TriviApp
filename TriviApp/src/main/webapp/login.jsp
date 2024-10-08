<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar sesión</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <div class="container mt-5">
            <div class="row">
                <div class="col-xl-3"></div>
                <div class="col-xl-6">
                    <h3 class="text-center mt-5">Iniciar sesión</h3>

                    <form id="form-login" action="${pageContext.request.contextPath}/login?accion=log" method="POST">
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" name="usuario" placeholder="Ingrese código" autofocus required>
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" class="form-control" name="password" placeholder="Contraseña" required>
                        </div>
                        <div class="text-center">
                            <button type="reset" class="btn btn-info mr-2">Limpiar</button>
                            <button type="submit" class="btn btn-success">Iniciar sesión</button>
                        </div>

                        <% 
                            // Mostrar error de login si existe
                            String errorLogin = (String) request.getAttribute("errorLogin");
                            if (errorLogin != null && !errorLogin.isEmpty()) { 
                        %>
                            <div class="alert alert-danger alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%= errorLogin %>
                            </div>
                        <% } %>
                    </form>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarLogin.js"></script>
    </body>
</html>