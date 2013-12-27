/*
This file is part of Gloo.

Gloo is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gloo is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gloo.  If not, see <http://www.gnu.org/licenses/>.
*/

package gloo;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import play.Logger;
import play.Play;
import play.api.libs.Files;
import play.libs.Crypto;
import play.libs.F.None;
import play.libs.F.Option;
import play.libs.F.Some;

public class PastesManager {
	private static String dataDirPath = "data";

	public static void save(String key, String content) {
		Files.writeFile(getNewPasteFile(key), content);
		Logger.info("[+][" + key + "][" + Crypto.sign ( content ) + "]");
	}

	public static Option<String> load(String key) {
		Option<File> f = getPasteFile(key);
		if (f.isDefined()) {
			f.get().setLastModified(new Date().getTime());
			return new Some<String>(Files.readFile(f.get()));
		} else
			return new None<String>();
	}

	/*public static Option<List<String>> loadLines(String key) {
		Option<InputStream> f = getPasteInputStream(key);
		if (f.isDefined())
			try {
				return new Some<List<String>>(IOUtils.readLines(f.get()));
			} catch (IOException e) {
				return new None<List<String>>();
			}
		else
			return new None<List<String>>();
	}*/

	public static void delete(String key) {
		Option<File> f = getPasteFile(key);
		if (f.isDefined()) {
			f.get().delete();
			Logger.info("[-][" + key + "]");
		}
	}

	public static Option<File> getPasteFile (String key) {
		File f = new File(getPastesDir().getPath() + File.separator + key);
		if (f.exists())
			return new Some<File>(f);
		else
			return new None<File>();
	}

	public static Option<InputStream> getPasteInputStream (String key) {
		File f = new File(getPastesDir().getPath() + File.separator + key);
		if (f.exists())
			return new Some<InputStream>(Play.application().resourceAsStream(f.toString()));
		else
			return new None<InputStream>();
	}

	public static File getNewPasteFile (String key) {
		return new File(getPastesDir().getPath() + File.separator + key);
	}

	public static File getPastesDir () {
		return Play.application().getFile(dataDirPath);
	}

	public static boolean isKeyAviable (String key) {
		Option<File> f = getPasteFile(key);
		if (f.isDefined()) return false;
		return true;
	}

	public static String getAviableKey ()
	{
		String key = KeyGenerator.getNewKey();
		while (!isKeyAviable(key)) {
			key = KeyGenerator.getNewKey();
		}
		return key;
	}

	public static void deleteAll ()
	{
		File f = getPastesDir();
		if (f.exists()) {
            File[] pastes = f.listFiles();
            for (File paste: pastes) {
            	paste.delete();
            }
            f.delete();
            Logger.info("[-][*]");
        }
	}

	public static void deleteOld ()
	{
		Logger.info("[c][started]");
		long maxAge = Play.application().configuration().getMilliseconds("cleanning.maxAge");
		long oblivion = new Date ().getTime () - maxAge;
		File f = getPastesDir();
		if (f.exists()) {
            File[] pastes = f.listFiles();
            for (File paste: pastes) {
            	if (paste.lastModified () < oblivion) {
            		paste.delete();
            		Logger.info("[c][-][" + paste.getName () + "]");
            	}
            }
        }
		Logger.info("[c][finished]");
	}
}
