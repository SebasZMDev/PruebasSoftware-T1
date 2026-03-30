import java.time.LocalDate;

public class RegistroDevoluciones {

    public String registrar(String codigoDevolucion, String codigoProducto,
                            String nombreCliente, String motivo,
                            LocalDate fechaCompra, LocalDate fechaDevolucion) {

        if (codigoDevolucion == null || codigoDevolucion.isEmpty() ||
                codigoProducto == null || codigoProducto.isEmpty() ||
                nombreCliente == null || nombreCliente.isEmpty() ||
                motivo == null || motivo.isEmpty())
            return "Debe ingresar todos los datos requeridos";

        if (!codigoDevolucion.matches("D\\d{4}"))
            return "Ingrese un código de devolución válido";

        if (!codigoProducto.matches("[a-zA-Z0-9]{6}"))
            return "Ingrese un código de producto válido";

        if (!nombreCliente.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]{4,}"))
            return "El nombre del cliente debe tener al menos cuatro caracteres alfabéticos";

        if (motivo.length() < 10)
            return "El motivo de la devolución debe tener al menos diez caracteres";

        if (fechaDevolucion.isBefore(fechaCompra) || fechaDevolucion.isAfter(LocalDate.now()))
            return "Ingrese una fecha de devolución válida";

        return "La devolución ha sido registrada correctamente";
    }
}