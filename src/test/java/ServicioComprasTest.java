import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicioComprasTest {

    @Mock
    private ProveedorService proveedorService;

    @Mock
    private AprobacionService aprobacionService;

    @InjectMocks
    private ServicioCompras servicioCompras;

    @Test
    @DisplayName("Proveedor inactivo retorna error de proveedor")
    void testProveedorInactivo() {
        when(proveedorService.estaActivo("PROV01")).thenReturn(false);

        String resultado = servicioCompras.registrarCompra(
                "PROV01", "ORD001", 500.0, true, "2025-12-31");

        assertEquals("El proveedor no esta activo", resultado);
        verify(proveedorService).estaActivo("PROV01");
    }

    @Test
    @DisplayName("Monto supera limite sin aprobacion retorna error de aprobacion")
    void testMontoSuperaLimiteSinAprobacion() {
        when(proveedorService.estaActivo("PROV01")).thenReturn(true);
        when(aprobacionService.tieneAprobacion("ORD001")).thenReturn(false);

        String resultado = servicioCompras.registrarCompra(
                "PROV01", "ORD001", 10000.0, false, "2025-12-31");

        assertEquals("La compra requiere aprobacion", resultado);
        verify(aprobacionService).tieneAprobacion("ORD001");
    }
}