package com.ctac.libraries;

import com.ctac.bean.MenuPerfil;
import com.ctac.service.ServiceAccesos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Menu_recursivo<T> {
	private ServiceAccesos serviceAccesos = new ServiceAccesos();
	public int nivel = -1;
	public String _html = "";

	public ArrayList<MenuPerfil> busca(ArrayList<MenuPerfil> menus, int padre, int idPerfil) {
		ArrayList data = new ArrayList();
		Iterator arg5 = menus.iterator();

		while (arg5.hasNext()) {
			MenuPerfil mp = (MenuPerfil) arg5.next();
			if (mp.getIdPerfil() == idPerfil && mp.getPadre() == padre) {
				data.add(mp);
			}
		}

		Collections.sort(data);
		return data;
	}

	public String muestra_menu(int padre, String nivelAnterior, String contextPath, int idPerfil,
			ArrayList<MenuPerfil> menus) {
		ArrayList datamenus = this.busca(menus, padre, idPerfil);

		for (int i = 0; i < datamenus.size(); ++i) {
			if (this.nivel == -1) {
				this._html = this._html + "<li id=\"lim_" + ((MenuPerfil) datamenus.get(i)).getIdMenu()
						+ "\" class=\"treeview\" >\n";
			} else {
				int urldb = ((MenuPerfil) datamenus.get(i)).getNivel() - this.nivel;
				if (urldb == 0) {
					this._html = this._html + "</li>\n<li id=\"lim_" + ((MenuPerfil) datamenus.get(i)).getIdMenu()
							+ "\" class=\"treeview\">\n";
				}

				if (urldb == 1) {
					this._html = this._html + "<ul class=\"treeview-menu\">\n<li id=\"lim_"
							+ ((MenuPerfil) datamenus.get(i)).getIdMenu() + "\" >\n";
				}

				if (urldb == -1) {
					this._html = this._html + "</li>\n</ul>\n<li id=\"lim_"
							+ ((MenuPerfil) datamenus.get(i)).getIdMenu() + "\" >\n";
				}

				if (urldb < -1) {
					this._html = this._html + "</li>";

					for (int parent = urldb; parent < 0; ++parent) {
						this._html = this._html + "</ul>\n</li>\n";
					}

					this._html = this._html + "<li id=\"lim_" + ((MenuPerfil) datamenus.get(i)).getIdMenu() + "\" >\n";
				}
			}

			String arg9 = !"".equals(((MenuPerfil) datamenus.get(i)).getUrl())
					&& ((MenuPerfil) datamenus.get(i)).getUrl() != null
							? contextPath + "/" + ((MenuPerfil) datamenus.get(i)).getUrl()
							: "javascript:void(0)";
			String arg10 = ((MenuPerfil) datamenus.get(i)).getPadre() == 0
					&& ((MenuPerfil) datamenus.get(i)).getUrl() == null
							? "<i class=\"fa fa-angle-left pull-right\"></i>"
							: "";
			this._html = this._html + "<a id=\"m_" + ((MenuPerfil) datamenus.get(i)).getIdMenu() + "\" href=\'" + arg9
					+ "\'><i class=\"" + ((MenuPerfil) datamenus.get(i)).getIcono() + "\"></i><span>"
					+ ((MenuPerfil) datamenus.get(i)).getNombreMenu() + "</span>" + arg10 + "</a>";
			this.nivel = ((MenuPerfil) datamenus.get(i)).getNivel();
			this.muestra_menu(((MenuPerfil) datamenus.get(i)).getIdMenu(),
					String.valueOf(((MenuPerfil) datamenus.get(i)).getNivel()), contextPath, idPerfil, menus);
		}

		return this._html;
	}

	public String muestra_menu_familias(String contextPath, int idPerfil) {
		ArrayList datamenus = this.serviceAccesos.get_menus_accesos_perfiles();
		System.out.println("muestra_menu_familias" + datamenus.size());
		String string = "";
		string = string + this.muestra_menu(0, "", contextPath, idPerfil, datamenus);
		string = string + "</li>\n";

		for (int i = 0; i == this.nivel; ++i) {
			;
		}

		return string;
	}

	public String muestra_menu_accesos(int padre, String nivelAnterior, String contextPath, int idPerfil,
			ArrayList<MenuPerfil> menus) {
		ArrayList datamenus = this.busca(menus, padre, idPerfil);

		for (int i = 0; i < datamenus.size(); ++i) {
			if (this.nivel == -1) {
				this._html = this._html + "<li>\n";
			} else {
				int urldb = ((MenuPerfil) datamenus.get(i)).getNivel() - this.nivel;
				if (urldb == 0) {
					this._html = this._html + "</li>\n<li>\n";
				}

				if (urldb == 1) {
					this._html = this._html + "<ul>\n<li>\n";
				}

				if (urldb == -1) {
					this._html = this._html + "</li>\n</ul>\n<li>\n";
				}

				if (urldb < -1) {
					this._html = this._html + "</li>";

					for (int checked = urldb; checked < 0; ++checked) {
						this._html = this._html + "</ul>\n</li>\n";
					}

					this._html = this._html + "<li>\n";
				}
			}

			String arg10 = ((MenuPerfil) datamenus.get(i)).getIdPerfil() == 0 ? "" : "checked";
			String classtipo = ((MenuPerfil) datamenus.get(i)).getNroh() == 0 ? "file" : "folder";
			if (!"null".equals(((MenuPerfil) datamenus.get(i)).getUrl())
					&& ((MenuPerfil) datamenus.get(i)).getUrl() != null) {
				(new StringBuilder(String.valueOf(contextPath))).append("/")
						.append(((MenuPerfil) datamenus.get(i)).getUrl()).toString();
			} else {
				String arg9999 = "javascript:void(0)";
			}

			this._html = this._html + "<span class=\"" + classtipo + "\" ><input type=\'checkbox\' " + arg10
					+ " value=\'" + ((MenuPerfil) datamenus.get(i)).getIdMenu() + "\' id=\'ckpermisos\' >"
					+ ((MenuPerfil) datamenus.get(i)).getNombreMenu() + "</span>";
			this.nivel = ((MenuPerfil) datamenus.get(i)).getNivel();
			this.muestra_menu_accesos(((MenuPerfil) datamenus.get(i)).getIdMenu(),
					String.valueOf(((MenuPerfil) datamenus.get(i)).getNivel()), contextPath, idPerfil, menus);
		}

		return this._html;
	}

	public String muestra_familias_accesos(String contextPath, int idPerfil) {
		ArrayList datamenus = this.serviceAccesos.get_permisos_menus_accesos();
		String string = "";
		string = string + this.muestra_menu_accesos(0, "", contextPath, idPerfil, datamenus);
		string = string + "</li>\n";

		for (int i = 0; i == this.nivel; ++i) {
			string = string + "</ul>\n</li>\n";
		}

		return string;
	}
}