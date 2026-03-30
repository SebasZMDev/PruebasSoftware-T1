import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RegistroDevolucionesTest {

    private RegistroDevoluciones registro;

    @BeforeEach
    void setUp() {
        registro = new RegistroDevoluciones();
    }

    @Test
    @DisplayName("Campo vacio en codigo de devolucion retorna error de datos requeridos")
    void testCampoVacioCodigoDevolucion() {
        String resultado = registro.registrar(
                "", "ABC123", "Carlos",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals("Debe ingresar todos los datos requeridos", resultado);
    }

    @Test
    @DisplayName("Codigo de devolucion sin letra D retorna error de codigo invalido")
    void testCodigoDevolucionSinLetraD() {
        String resultado = registro.registrar(
                "X1234", "ABC123", "Carlos",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals("Ingrese un código de devolución válido", resultado);
    }

    @Test
    @DisplayName("Codigo de producto con caracteres especiales retorna error de codigo invalido")
    void testCodigoProductoConCaracteresEspeciales() {
        String resultado = registro.registrar(
                "D1234", "AB-123", "Carlos",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals("Ingrese un código de producto válido", resultado);
    }

    @Test
    @DisplayName("Nombre de cliente con numeros retorna error de nombre invalido")
    void testNombreClienteConNumeros() {
        String resultado = registro.registrar(
                "D1234", "ABC123", "Carl0s",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(1), LocalDate.now());
        assertEquals("El nombre del cliente debe tener al menos cuatro caracteres alfabéticos", resultado);
    }

    @Test
    @DisplayName("Fecha de devolucion futura retorna error de fecha invalida")
    void testFechaDevolucionFutura() {
        String resultado = registro.registrar(
                "D1234", "ABC123", "Carlos",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        assertEquals("Ingrese una fecha de devolución válida", resultado);
    }

    @Test
    @DisplayName("Todos los datos validos registra la devolucion correctamente")
    void testRegistroExitoso() {
        String resultado = registro.registrar(
                "D1234", "ABC123", "Carlos",
                "Producto defectuoso desde el inicio",
                LocalDate.now().minusDays(3), LocalDate.now());
        assertEquals("La devolución ha sido registrada correctamente", resultado);
    }
}