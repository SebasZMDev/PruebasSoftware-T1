import java.time.LocalDate;

public class RegistroDevoluciones {

    private static final String REGEX_CODIGO_DEVOLUCION = "D\\d{4}";
    private static final String REGEX_CODIGO_PRODUCTO   = "[a-zA-Z0-9]{6}";
    private static final String REGEX_NOMBRE_CLIENTE    = "[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]{4,}";

    public String registrar(String codigoDevolucion, String codigoProducto,
                            String nombreCliente, String motivo,
                            LocalDate fechaCompra, LocalDate fechaDevolucion) {

        if (tieneCamposVacios(codigoDevolucion, codigoProducto, nombreCliente, motivo))
            return "Debe ingresar todos los datos requeridos";

        if (!codigoDevolucion.matches(REGEX_CODIGO_DEVOLUCION))
            return "Ingrese un código de devolución válido";

        if (!codigoProducto.matches(REGEX_CODIGO_PRODUCTO))
            return "Ingrese un código de producto válido";

        if (!nombreCliente.matches(REGEX_NOMBRE_CLIENTE))
            return "El nombre del cliente debe tener al menos cuatro caracteres alfabéticos";

        if (motivo.length() < 10)
            return "El motivo de la devolución debe tener al menos diez caracteres";

        if (!esFechaValida(fechaCompra, fechaDevolucion))
            return "Ingrese una fecha de devolución válida";

        return "La devolución ha sido registrada correctamente";
    }

    private boolean tieneCamposVacios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.isEmpty()) return true;
        }
        return false;
    }

    private boolean esFechaValida(LocalDate fechaCompra, LocalDate fechaDevolucion) {
        return !fechaDevolucion.isBefore(fechaCompra)
                && !fechaDevolucion.isAfter(LocalDate.now());
    }
}