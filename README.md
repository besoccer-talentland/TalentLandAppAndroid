## TalentLand Android Workshop – Commit Levels

Figma : https://www.figma.com/design/3aze8cxnM0hgyvpfab06Ff/Home?node-id=1-2&t=MFalMwgUYThwdSLn-1
<img width="411" height="838" alt="image" src="https://github.com/user-attachments/assets/caab5b98-5af5-4b12-92d4-b45f31ff5d0c" />


Este repo está preparado para que los asistentes se puedan enganchar al taller en distintos niveles de dificultad usando los commits.

### Nivel 1 – Base + Hello World
- **Commit**: `feat: setup base android app for workshop`
- **Qué hay**:
  - Configuración de Gradle, plugins y tema `Theme.TalentLand`.
  - `MainActivity` en Jetpack Compose mostrando solo un **Hello World** ("Hello Talent Land").
- **Objetivo didáctico**:
  - Entender estructura básica de un proyecto moderno (Compose, Material3).
  - Que cualquiera pueda clonar, abrir en Android Studio y ejecutar sin tocar nada.

### Nivel 2 – Hilt integrado
- **Commit**: `feat: integrate hilt dependency injection`
- **Qué hay**:
  - `TalentLandApplication` con `@HiltAndroidApp` declarada en el `AndroidManifest`.
  - `MainActivity` anotada con `@AndroidEntryPoint`.
- **Qué NO hay todavía**:
  - No se está usando ningún `ViewModel` ni inyecciones complejas.
  - La UI visible sigue siendo el **Hello World**.
- **Objetivo didáctico**:
  - Ver la configuración mínima realista de Hilt en una app profesional.

### Nivel 3 – Infraestructura de Delegate Adapters
- **Commit**: `feat: add delegate adapter infrastructure for matches list`
- **Qué hay**:
  - Infraestructura genérica de listas:
    - `core/CornerStyle.kt`
    - `core/adapter/DelegateAdapter.kt`
    - `core/adapter/DelegateAdapterItem.kt`
    - `core/adapter/CompositeAdapter.kt`
    - `core/viewholder/BaseViewHolder.kt`
- **Qué NO hay todavía**:
  - No hay adapters concretos de `Match`, ni layouts de item, ni lógica de RecyclerView.
  - `MainActivity` sigue mostrando solo el **Hello World**.
- **Objetivo didáctico**:
  - Explicar el patrón de **delegate adapters** sin mezclar todavía la lógica de negocio.
  - A partir de aquí, los asistentes pueden crear sus propios adapters concretos.

### Nivel 4 – Arquitectura completa (hasta ViewModel)
- **Commit**: `feat: implement matches architecture with viewmodel and data layer`
- **Qué hay**:
  - **Capa domain**:
    - `Match`, `MatchStatus`, `MatchRepository`, `GetMatchesUseCase`.
  - **Capa data**:
    - API (`MatchApiService`, `ApiConstants`, `MatchDto`).
    - Mapper `MatchMapper` (`MatchDto.toDomain()`).
    - `MatchRepositoryImpl` con `Flow<Result<List<Match>>>`.
  - **DI con Hilt**:
    - `NetworkModule` (OkHttp + Retrofit).
    - `RepositoryModule` (bind de `MatchRepositoryImpl`).
  - **Presentación**:
    - `MatchUiState`.
    - `MatchViewModel` inyectado con Hilt.
    - `MatchesXmlFragment` mínimo que inyecta el ViewModel y usa `fragment_matches.xml` (layout placeholder).
- **Qué ve el usuario al ejecutar este commit**:
  - **Sigue viendo el Hello World** en `MainActivity`.
  - Toda la arquitectura está lista “por debajo”, pero la UI real de partidos y los adapters concretos se añaden en un commit posterior.
- **Objetivo didáctico**:
  - Enseñar cómo encajar capas **data/domain/presentation** con Hilt sin distraerse todavía con la UI.

### Nivel extra – UI completa de partidos (para que lo implementes tú)
- **Pendiente de crear por ti** (no está en el historial de commits de este repo).
- **Sugerencia de commit**:  
  - `feat: add matches ui with delegate adapters and compose/xml switch`
- **Qué puedes incluir aquí**:
  - Adapters concretos:
    - `MatchDelegateAdapter`, `MatchDelegateAdapterItem`, `MatchViewHolder`.
  - Layouts:
    - `item_match.xml`, versión final de `fragment_matches.xml` con RecyclerView, toolbar, estados loading/error, FAB de refresh…
  - UI:
    - `MatchesXmlFragment` completo, observando `MatchViewModel` y alimentando los adapters.
    - `MatchesComposeScreen` y `XmlFragmentContainer` (y, si quieres, el toggle Compose/XML en `MainActivity`).
