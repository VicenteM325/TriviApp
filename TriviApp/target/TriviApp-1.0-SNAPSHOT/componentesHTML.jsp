<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row my-5">
                <div class="col-2"></div>
                <div class="col-8">
                    <div class="card my-5 text-white">
                        <div class="card-header text-center bg-dark">
                            <h4>Titulo del formualario</h4>
                        </div>
                        <div class="card-body bg-secondary">

                            <form id="form-id" action="${pageContext.request.contextPath}/readData" method="POST" enctype="multipart/form-data">
                                <!-- Clase tipo campo texto -->
                                <div class="form-group">
                                    <label for="campoTexto">Campo Texto</label>
                                    <input type="text" class="form-control" name="campoTexto" value="">
                                </div>

                                <!-- Clase tipo area texto -->
                                <div class="form-group">
                                    <label for="areaTexto">Area texto:</label>
                                    <textarea id="area" class="form-control" name="areaTexto" rows="5" cols="15"></textarea>
                                </div>

                                <!-- Clase tipo checkbox -->
                                <label for="check">Checkbox</label>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="check" value="">Option 1
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="check" value="">Option 2
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="check" value="">Option 3
                                    </label>
                                </div>

                                <!-- Clase tipo radio button -->
                                <label for="algo" class="mt-3">Radio</label>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="radio" value="">Option 1
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="radio" value="">Option 2
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="radio" value="">Option 3
                                    </label>
                                </div>

                                <!-- Clse de tipo fichero -->
                                <div class="form-group mt-3">
                                    <label for="file">Fichero</label>
                                    <input type="file" class="form-control-file border" name="file" accept=".*">
                                </div>

                                <!-- Clase tipo combobox -->
                                <div class="form-group">
                                    <label for="combo">Combo</label>
                                    <select class="form-control" name="combo" id="combo">
                                        <option>Seleccione un opcion...</option>
                                        <option>Holi1</option>
                                        <option>Holi2</option>
                                        <option>Holi3</option>
                                        <option>Holi4</option>
                                    </select>
                                </div>

                                <!-- Clase tipo boton -->
                                <label for="boton">Boton</label>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary btn-block">Boton</button>
                                </div>
                            </form>
                                
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
