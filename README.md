# appQuestionBank

Banco de preguntas de escritorio (Java Swing + SQLite).

## Arquitectura

Monolito por capas que incorpora dos estilos:

- **Tuberías y filtros** (`pkgServices.pkgPipeline`): toda la validación de datos
  (usuarios y preguntas) pasa por tuberías de filtros encadenados.
- **Microkernel** (`pkgServices.pkgMicrokernel`): un núcleo que carga por reflexión
  los plugins de tipos de pregunta definidos en `plugins.properties`.

Las dependencias van en un solo sentido:

```
pkgPresentation ──► pkgController ──► pkgServices ─────┐
                         │      └───► pkgPersistence ──┤
                         └──────────► pkgDomain ◄──────┘
                                         │
                                         ▼
                                      pkgUtil
```

`pkgDomain` no depende de la base de datos ni de la interfaz gráfica.

## Estructura

```
appQuestionBank/
├── lib/                         jbcrypt, sqlite-jdbc
│   └── test/                    junit, hamcrest (solo pruebas)
├── data/                        data_base.db (se crea sola)
└── src/
    ├── main/java/
    │   ├── pkgApp/              App (punto de entrada)
    │   ├── pkgPresentation/     ventanas Swing (uiLogin, uiQuestion, ...)
    │   ├── pkgController/       clsController: fachada / casos de uso
    │   ├── pkgDomain/
    │   │   ├── pkgEntities/     clsEntity, clsUser, clsRole, clsQuestion
    │   │   └── pkgRepository/   clsControllerDomain (repositorio en memoria)
    │   ├── pkgServices/
    │   │   ├── pkgPipeline/     IFilterText, IFilterOption, tuberías y fábrica
    │   │   │   └── pkgFilters/  filtros concretos
    │   │   ├── pkgMicrokernel/
    │   │   │   ├── pkgKernel/   IQuestionPlugin, clsQuestionMicrokernel
    │   │   │   └── pkgPlugins/  selección múltiple, caso, multimedia
    │   │   └── pkgSecurity/     clsSecurityUtils (BCrypt)
    │   ├── pkgPersistence/      conexión SQLite y DAO
    │   └── pkgUtil/             clsBrokerCrud (utilidades genéricas)
    ├── main/resources/
    │   ├── icons/
    │   └── plugins.properties
    └── test/java/               pruebas JUnit 4
```

## Flujo para registrar una pregunta

`clsController.opRegisterQuestion` →
1. **Pipeline**: valida nombre, enunciado, opciones, respuesta correcta y estado.
2. **Microkernel**: crea la pregunta con el plugin de su tipo.
3. **Persistencia**: la guarda en SQLite.
4. **Repositorio**: la agrega a memoria.

## Agregar un tipo de pregunta nuevo

1. Crear una clase en `pkgServices.pkgMicrokernel.pkgPlugins` que implemente `IQuestionPlugin`.
2. Registrarla en `src/main/resources/plugins.properties`.

El núcleo no se modifica.

## Ejecutar

Abrir la carpeta en VS Code (Extension Pack for Java) y ejecutar `pkgApp.App`.
Ejecutar desde la raíz del proyecto, porque la base se guarda en `data/`.

Al arrancar por primera vez se crean las tablas y los roles por defecto
(Administrador, Autor de preguntas, Revisor, Docente, Estudiante).

## Pruebas

Las pruebas están en `src/test/java` y se ejecutan desde la pestaña *Testing* de VS Code.
