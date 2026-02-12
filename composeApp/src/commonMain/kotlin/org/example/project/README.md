# 🤺 EsgrimaApp - Gestión de Competiciones

Aplicación multiplataforma diseñada para la gestión integral de torneos de esgrima, permitiendo la administración de tiradores, árbitros, generación automática de poules y cuadros de asaltos.

---

## 🔑 Acceso al Sistema

Para facilitar las pruebas de desarrollo, el sistema cuenta con diferentes niveles de acceso según el rol:

### 🛠️ Modo Administrador
Permite crear competiciones, gestionar participantes y visualizar clasificaciones globales.
* **Usuario:** `admin`
* **Contraseña:** `admin`

### 🤺 Modo Árbitro
Permite gestionar las poules asignadas y visualizar los cuadros de asaltos.
* **Acceso general:** El nombre de usuario y la contraseña coinciden con el **nombre del árbitro** creado previamente por el administrador.
* **Usuarios de prueba (Seed Data):**
    * **Árbitro 1:** User: `Pepe` / Pass: `Pepe`
    * **Árbitro 2:** User: `Juan` / Pass: `Juan`

> [!NOTE]
> Al iniciar la aplicación, la clase `CompetitionStore` genera automáticamente una competición de prueba ("Copa del Rey - Test") con 8 tiradores y los dos árbitros mencionados arriba para permitir pruebas inmediatas de la lógica de poules y asaltos.

---

## 🚀 Características Principales
* **Generación Automática de Poules:** Reparto equitativo de tiradores entre árbitros disponibles.
* **Matriz de Puntuación:** Interfaz intuitiva para que los árbitros anoten resultados en tiempo real.
* **Cuadro de Asaltos (Brackets):** Generación automática del "Top 8" basada en los resultados de las poules.
* **Clasificaciones:** Ranking actualizado por puntos en tiempo real.