package servlet;



import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import repositorio.UsuarioRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CrearUsuario")
public class CreateServlet extends HttpServlet {
    /**
     * Called by the server (via the <code>service</code> method) to allow a servlet to handle a GET request.
     *
     * <p>
     * Overriding this method to support a GET request also automatically supports an HTTP HEAD request. A HEAD request is a
     * GET request that returns no body in the response, only the request header fields.
     *
     * <p>
     * When overriding this method, read the request data, write the response headers, get the response's writer or output
     * stream object, and finally, write the response data. It's best to include content type and encoding. When using a
     * <code>PrintWriter</code> object to return the response, set the content type before accessing the
     * <code>PrintWriter</code> object.
     *
     * <p>
     * The servlet container must write the headers before committing the response, because in HTTP the headers must be sent
     * before the response body.
     *
     * <p>
     * Where possible, set the Content-Length header (with the {@link ServletResponse#setContentLength}
     * method), to allow the servlet container to use a persistent connection to return its response to the client,
     * improving performance. The content length is automatically set if the entire response fits inside the response
     * buffer.
     *
     * <p>
     * When using HTTP 1.1 chunked encoding (which means that the response has a Transfer-Encoding header), do not set the
     * Content-Length header.
     *
     * <p>
     * The GET method should be safe, that is, without any side effects for which users are held responsible. For example,
     * most form queries have no side effects. If a client request is intended to change stored data, the request should use
     * some other HTTP method.
     *
     * <p>
     * The GET method should also be idempotent, meaning that it can be safely repeated. Sometimes making a method safe also
     * makes it idempotent. For example, repeating queries is both safe and idempotent, but buying a product online or
     * modifying data is neither safe nor idempotent.
     *
     * <p>
     * If the request is incorrectly formatted, <code>doGet</code> returns an HTTP "Bad Request" message.
     *
     * @param req  an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     * @throws ServletException if the request for the GET could not be handled
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String rut = req.getParameter("rut");
        UsuarioRepo usuarioRep = new UsuarioRepo();
        Map<String, String> errores = new HashMap<String, String>();

        if(nombre == null || nombre.trim().isEmpty()) {
            errores.put("nombre", "debe ingresar un nombre válido");
        }
        if(apellido == null || apellido.trim().isEmpty()) {
            errores.put("apellido", "debe ingresar un apellido válido");
        }
        if(rut == null || rut.trim().isEmpty()) {
            errores.put("rut", "debe ingresar un rut válido");
        }
        if(errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                UsuarioRepo usuarioRepo = new UsuarioRepo();
                Usuario usuario = new Usuario(nombre, apellido, rut);
                try {
                    usuarioRepo.crear(usuario);
                    resp.sendRedirect("exito.jsp");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }else {
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
