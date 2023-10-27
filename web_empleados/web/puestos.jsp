<%-- 
    Document   : puestos
    Created on : 12 oct 2023, 17:07:00
    Author     : pc01
--%>
<%@page import="modelo.Puesto" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Puestos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Puestos</h1>
        <a href="empleados.jsp" class="nav-link active" aria-current="page">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>
        back
        </a>
        <button type="button" class="btn btn-info btn-lg" data-bs-toggle="modal" data-bs-target="#modal_puesto" onclick="limpiar()">Nuevo</button>
        
        <div class="container">
            
            <div class="modal fade" id="modal_puesto" role="dialog" <!--tabindex="-1"-->>
                <div class="modal-dialog">
                    
                  <!-- Modal content -->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="sr_puesto" method="post" class="form-group">
                            <label for="lbl_id"><b>ID:</b></label>
                            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                            <label for="lbl_puesto"><b>Puesto:</b></label>
                            <input type="text" name="txt_puesto" id="txt_puesto" class="form-control" placeholder="Ejemplo: Programador" required>
                            <br>
                            <div class="btn-group" role="group">
                                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success">Agregar</button>
                                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-warning">Modificar</button>
                                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                            </div>
                            <button name="btn_limpiar" id="btn_limpiar" value="limpiar" class="btn btn-secondary" onclick="limpiar()">Limpiar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                  </div>
                </div>
              </div>
            
            
                <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">Puestos</th>
                  </tr>
                </thead>
                <tbody id="tbl_puestos">
                    <!-- Contenido de la Tabla -->
                    <%
                        Puesto puesto = new Puesto();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = puesto.leer();
                        for(int t=0;t<tabla.getRowCount();t++){
                            out.println("<tr data-id=" + tabla.getValueAt(t,0) + ">");
                            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
                            out.println("</tr>");
                        }
                    %>
                    
                </tbody>
              </table>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
        <script type="text/javascript">
            <!-- Funcion para limpiar el formulario -->
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_puesto").val('');
            }
            
            <!-- Evento mouse clicked -->
            $('#tbl_puestos').on('click','tr td',function(evt){
                var target,id,puesto; 
                target = $(event.target);
                id = target.parent().data('id');
                puesto = target.parent("tr").find("td").eq(0).html(); 
                $("#txt_id").val(id);
                $("#txt_puesto").val(puesto);
                $("#modal_puesto").modal('show');
             });
            
        </script>
        
        
    </body>
</html>
