package com.stockfacil.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ronny
 */
import com.stockfacil.dao.ProveedorDAO;
import com.stockfacil.model.Proveedor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/proveedores")
public class ProveedorServlet extends HttpServlet {

    private final ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");

        try {
            if ("nuevo".equals(accion)) {
                req.setAttribute("proveedor", new Proveedor());
                mostrar("proveedores/form", req, resp);
            } else if ("editar".equals(accion)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Proveedor p = dao.buscarPorId(id);

                if (p == null) {
                    resp.sendRedirect("proveedores");
                    return;
                }

                req.setAttribute("proveedor", p);
                mostrar("proveedores/form", req, resp);
            } else {
                req.setAttribute("proveedores", dao.listar());
                mostrar("proveedores/lista", req, resp);
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");

        try {
            if ("desactivar".equals(accion)) {
                dao.desactivar(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("proveedores");
                return;
            }

            Proveedor p = new Proveedor();

            String id = req.getParameter("idProveedor");
            p.setIdProveedor(id == null || id.isBlank() ? 0 : Integer.parseInt(id));

            p.setNombre(req.getParameter("nombre") == null ? "" : req.getParameter("nombre").trim());
            p.setCedulaJuridica(req.getParameter("cedulaJuridica") == null ? "" : req.getParameter("cedulaJuridica").trim());
            p.setTelefono(req.getParameter("telefono") == null ? "" : req.getParameter("telefono").trim());
            p.setCorreo(req.getParameter("correo") == null ? "" : req.getParameter("correo").trim());
            p.setActivo(req.getParameter("activo") != null || p.getIdProveedor() == 0);

            List<String> errores = new ArrayList<>();

            if (p.getNombre().isEmpty()) {
                errores.add("El nombre es obligatorio.");
            }

            if (p.getCedulaJuridica().isEmpty()) {
                errores.add("La cédula jurídica es obligatoria.");
            } else if (dao.existeCedula(p.getCedulaJuridica(), p.getIdProveedor())) {
                errores.add("Ya existe un proveedor con esa cédula jurídica.");
            }

            if (!p.getTelefono().isEmpty() && !p.getTelefono().matches("\\d{8}")) {
                errores.add("El teléfono debe tener 8 dígitos.");
            }

            if (!p.getCorreo().isEmpty() && !p.getCorreo().contains("@")) {
                errores.add("El correo no es válido.");
            }

            if (!errores.isEmpty()) {
                req.setAttribute("errores", errores);
                req.setAttribute("proveedor", p);
                mostrar("proveedores/form", req, resp);
                return;
            }

            if (p.getIdProveedor() == 0) {
                dao.insertar(p);
            } else {
                dao.actualizar(p);
            }

            resp.sendRedirect("proveedores");

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }

    private void mostrar(String vista, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/" + vista + ".jsp").forward(req, resp);
    }
}
