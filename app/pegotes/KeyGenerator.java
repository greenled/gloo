/*
This file is part of Pegotes.

Pegotes is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Pegotes is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Pegotes.  If not, see <http://www.gnu.org/licenses/>.
*/

package pegotes;

import java.util.UUID;

public class KeyGenerator {

	/**
	 * Obtener un nuevo identificador aleatorio
	 * @return el nuevo identificador aleatorio
	 */
	public static String getNewKey() {
		/*
		 * Esto es para probar con un espacio de claves de {0,1}
		 * return (new Date().getTime()%2) + "";
		 */

		return UUID.randomUUID().toString();
	}

}
