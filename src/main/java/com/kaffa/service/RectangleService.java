package com.kaffa.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RectangleService {

	public ResponseEntity<?> intersect(int[] rect1, int[] rect2) {

		try {

			if (!intersects(rect1, rect2))
				return ResponseEntity.ok().body(false);

			return ResponseEntity.ok().body(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public ResponseEntity<?> area(int[] rect1, int[] rect2) {

		try {

			if (areaOfIntersection(rect1, rect2) == 0)
				return ResponseEntity.ok().body(0);

			return ResponseEntity.ok().body(areaOfIntersection(rect1, rect2));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" [x] Error: " + e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	public static boolean intersects(int[] rect1, int[] rect2) {
		// Definição dos pontos de cada retângulo
		int Ax1 = rect1[0], Ay1 = rect1[1], Ax2 = rect1[2], Ay2 = rect1[3];
		int Bx1 = rect2[0], By1 = rect2[1], Bx2 = rect2[2], By2 = rect2[3];

		// Verificar as condições de interseção
		if (Ax2 < Bx1 || Bx2 < Ax1) { // A está completamente à esquerda ou à direita de B
			return false;
		}
		if (Ay2 < By1 || By2 < Ay1) { // A está completamente abaixo ou acima de B
			return false;
		}
		return true; // Caso contrário, eles se intersectam
	}

	public int areaOfIntersection(int[] rect1, int[] rect2) {
		// Definir os limites de cada retângulo
		int Ax1 = rect1[0], Ay1 = rect1[1], Ax2 = rect1[2], Ay2 = rect1[3];
		int Bx1 = rect2[0], By1 = rect2[1], Bx2 = rect2[2], By2 = rect2[3];

		// Encontrar os limites da interseção
		int xLeft = Math.max(Ax1, Bx1);
		int xRight = Math.min(Ax2, Bx2);
		int yBottom = Math.max(Ay1, By1);
		int yTop = Math.min(Ay2, By2);

		if (xLeft <= xRight && yBottom <= yTop) {
			int width = xRight - xLeft + 1;
			int height = yTop - yBottom + 1;
			return width * height;
		}

		return 0;
	}

}
