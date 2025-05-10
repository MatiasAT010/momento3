import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Tarea {
	String titulo;
	String estado;
	String prioridad;

//Constructor
	public Tarea(String titulo, String prioridad) {
		this.titulo = titulo;
		this.estado = "Pendiente";
		this.prioridad = prioridad;
	}

//To string
	public void mostrarDatos() {
		System.out.println("Título: " + titulo + " | Estado: " + estado + " | Prioridad: " + prioridad);
	}
}

public class ToDoList {
	//Hacemos un arreglo el cual va a contener las tareas
	static ArrayList<Tarea> tareas = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
//Clase main
	public static void main(String[] args) {
		int opcion;
//Lista de opciones, bucle do while para seguir ejecutando las lineas de codigo hasta que el usuario quiera salir
		do {
			System.out.println("1. Agregar tarea");
			System.out.println("2. Mostrar tareas");
			System.out.println("3. Cambiar estado");
			System.out.println("4. Eliminar tarea");
			System.out.println("5. Marcar tarea como completada");
			System.out.println("6. Mostrar tareas pendientes");
			System.out.println("7. Salir");
			System.out.println("8. Guardar en archivo");
			System.out.println("9. Cargar desde un archivo");
			System.out.print("Opción: ");
			opcion = sc.nextInt();
			sc.nextLine();
//Segun la opcion el programa llama la funcion
			if (opcion == 1)
				agregarTarea();
			else if (opcion == 2)
				mostrarTareas();
			else if (opcion == 3)
				cambiarEstado();
			else if (opcion == 4)
				eliminarTarea();
			else if (opcion == 5)
				marcarComoCompletada();
			else if (opcion == 6)
				mostrarTareasPendientes();
			else if (opcion == 7)
				System.out.println("Saliendo...");
			else if (opcion == 8)
				guardarEnArchivo();
			else if (opcion == 9)
				cargarDesdeArchivo();

			else
				System.out.println("Opción inválida");
		} while (opcion != 7);
	}
// Funcion para agregar 
	static void agregarTarea() {
		System.out.print("Título: ");
		String titulo = sc.nextLine();
		System.out.print("Prioridad (Alta/Media/Baja): ");
		String prioridad = sc.nextLine();
		tareas.add(new Tarea(titulo, prioridad));
	}

	static void mostrarTareas() {
		if (tareas.isEmpty()) {
			System.out.println("No hay tareas.");
			return;
		}
		for (Tarea t : tareas) {
			t.mostrarDatos();
		}
	}

	static void cambiarEstado() {
		mostrarTareas();
		System.out.print("Número de tarea para cambiar estado: ");
		int i = sc.nextInt() - 1;
		sc.nextLine();

		if (i >= 0 && i < tareas.size()) {
			System.out.print("Nuevo estado (Pendiente/En Proceso/Completada): ");
			tareas.get(i).estado = sc.nextLine();
		} else {
			System.out.println("Tarea no válida.");
		}
	}

	static void eliminarTarea() {
		mostrarTareas();
		System.out.print("Número de tarea a eliminar: ");
		int i = sc.nextInt() - 1;
		sc.nextLine();

		if (i >= 0 && i < tareas.size()) {
			tareas.remove(i);
			System.out.println("Tarea eliminada.");
		} else {
			System.out.println("Tarea no válida.");
		}
	}

	static void marcarComoCompletada() {
		mostrarTareas();
		System.out.print("Número de tarea para marcar como completada: ");
		int i = sc.nextInt() - 1;
		sc.nextLine();

		if (i >= 0 && i < tareas.size()) {
			tareas.get(i).estado = "Completada";
			System.out.println("Tarea marcada como completada.");
		} else {
			System.out.println("Tarea no válida.");
		}
	}

	static void mostrarTareasPendientes() {
		System.out.println("Tareas Pendientes:");
		for (Tarea t : tareas) {
			if (t.estado.equals("Pendiente")) {
				t.mostrarDatos();
			}
		}
	}

	static void guardarEnArchivo() {
		try {
			FileWriter fw = new FileWriter("tareas.txt");
			for (Tarea t : tareas) {
				fw.write(t.titulo + ";" + t.estado + ";" + t.prioridad + "\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("Error al guardar.");
		}
	}

	static void cargarDesdeArchivo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("tareas.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (datos.length == 3) {
					Tarea t = new Tarea(datos[0], datos[2]);
					t.estado = datos[1];
					tareas.add(t);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("No se encontró archivo de tareas (se creará uno nuevo al guardar).");
		}

	}
}
