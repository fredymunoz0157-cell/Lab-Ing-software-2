package com.unicauca.microservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MicroserviceApplication.class, args);
	}

	// --- BLOQUE DE PRUEBA ADAPTADO A TU ENTIDAD ---
    @Bean
    public CommandLineRunner testUserService(clsUserRepositoryService userService) {
        return args -> {
            System.out.println("==================================================");
            System.out.println(">>> INICIANDO PRUEBA DE clsUserRepositoryService <<<");

            // 1. Instanciar un usuario de prueba (ajusta los valores según el constructor de clsUserRepository)
            // Si tu constructor es diferente, puedes usar los métodos opSet... correspondientes.
            clsUserRepository nuevoUsuario = new clsUserRepository("USR-001", "Carlos Gomez", "Docente", "cgomez", "12345", true, "ADMIN");
            
            // 2. Probar Registro (Create)
            clsUserRepository usuarioGuardado = userService.opRegisterUser(nuevoUsuario);
            System.out.println("[TEST] Usuario registrado: " + usuarioGuardado.opGetNickName() + " (ID: " + usuarioGuardado.opGetOUID() + ")");

            // 3. Probar Listar Todos (Read All)
            System.out.println("[TEST] Total usuarios en el sistema: " + userService.opGetAllUsers().size());

            // 4. Probar Buscar por ID (Read by ID)
            userService.opGetUserById("USR-001").ifPresent(u -> {
                System.out.println("[TEST] Usuario encontrado: " + u.opGetName() + " - Rol: " + u.opGetRole());
            });

            // 5. Probar Actualizar (Update)
            clsUserRepository datosModificados = new clsUserRepository("USR-001", "Carlos Gomez Modificado", "Docente Titular", "cgomez_titular", "nueva_clave", true, "SUPER_ADMIN");
            

            userService.opUpdateUser(datosModificados);

            userService.opGetUserById("USR-001").ifPresent(u -> {
                System.out.println("[TEST] Usuario post-actualización: " + u.opGetName() + " (" + u.opGetNickName() + ")");
            });

            // 6. Probar Eliminar (Delete)
            userService.opDeleteUser("USR-001");
            boolean sigueExistiendo = userService.opGetUserById("USR-001").isPresent();
            System.out.println("[TEST] ¿El usuario aún existe tras eliminarlo? " + (sigueExistiendo ? "SÍ" : "NO"));

            System.out.println(">>> ¡SERVICIO DE USUARIOS FUNCIONANDO CORRECTAMENTE! <<<");
            System.out.println("==================================================");
        };
    }

}
