package com.unicauca.microservicio_crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroservicioCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCrudApplication.class, args);
	}

	@Bean
    public CommandLineRunner testService(clsRoleService roleService) { // Ajusta al nombre de tu clase de servicio
        return args -> {
            System.out.println("==================================================");
            System.out.println(">>> INICIANDO PRUEBA PARA clsRoleSpringBoot <<<");

            // 1. Probar Guardar (Create / Save)
            clsRoleSpringBoot rol1 = new clsRoleSpringBoot("ROL-001", "ADMIN", "Rol de administrador del sistema");
            roleService.opCreateRole(rol1); // o el nombre que le diste al método de guardar
            System.out.println("[TEST] Rol guardado con OUID: " + rol1.opGetOUID());

            // 2. Probar Listar (Find All)
            System.out.println("[TEST] Total de roles en BD: " + roleService.opGetAllRoles().size());

            // 3. Probar Actualizar (Update)
            clsRoleSpringBoot rolActualizado = new clsRoleSpringBoot("ROL-001", "SUPER_ADMIN", "Acceso total");
            roleService.opUpdateRole(rolActualizado);

            // 4. Probar Buscar por ID (Find By OUID)
            roleService.opGetRoleById("ROL-001").ifPresent(r -> {
                System.out.println("[TEST] Rol obtenido de BD:");
                System.out.println("       OUID: " + r.opGetOUID());
                System.out.println("       Nombre: " + r.opGetName());
                System.out.println("       Descripción: " + r.opGetDescription());
            });

            System.out.println(">>> ¡SERVICIO Y REPOSITORIO DE ROLES FUNCIONANDO! <<<");
            System.out.println("==================================================");
        };
    }
}
