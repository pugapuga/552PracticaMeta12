package paquete;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoTest {
    private Alumno alumno = new Alumno("Pepe");

    @org.junit.jupiter.api.Test
    void getNombre() {
        assertEquals("Pepe",alumno.getNombre());
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        alumno.setNombre("Juan");
        assertEquals("Juan",alumno.getNombre());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("Alumno{nombre='Pepe'}",alumno.toString());
    }
}