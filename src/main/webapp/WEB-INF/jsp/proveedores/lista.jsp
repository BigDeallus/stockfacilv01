<%-- 
    Document   : lista
    Created on : 30 oct 2026, 14:51:41
    Author     : Ronny
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../comun/encabezado.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h3">Proveedores</h1>
    <a class="btn btn-primary" href="proveedores?accion=nuevo">
        Nuevo proveedor
    </a>
</div>

<table class="table table-striped bg-white">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Cédula jurídica</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Estado</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="prov" items="${proveedores}">
            <tr>
                <td><c:out value="${prov.nombre}"/></td>
                <td><c:out value="${prov.cedulaJuridica}"/></td>
                <td><c:out value="${prov.telefono}"/></td>
                <td><c:out value="${prov.correo}"/></td>
                <td>${prov.activo ? 'Activo' : 'Inactivo'}</td>
                <td class="text-end">
                    <a class="btn btn-sm btn-outline-secondary"
                       href="proveedores?accion=editar&id=${prov.idProveedor}">
                        Editar
                    </a>

                    <c:if test="${prov.activo}">
                        <form method="post" action="proveedores"
                              class="d-inline"
                              onsubmit="return confirm('¿Desactivar este proveedor?')">
                            <input type="hidden" name="accion" value="desactivar">
                            <input type="hidden" name="id" value="${prov.idProveedor}">
                            <button type="submit" class="btn btn-sm btn-outline-danger">
                                Desactivar
                            </button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../comun/pie.jsp" %>
