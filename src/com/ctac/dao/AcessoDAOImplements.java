package com.ctac.dao;

import com.google.gson.Gson;
import com.ctac.bean.InfoUserBean;
import com.ctac.bean.MenuPerfil;
import com.ctac.bean.PerfilBean;
import com.ctac.bean.TableUsuarioBean;
import com.ctac.dao.HibernateUtil;
import com.ctac.dao.IAccesosDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AcessoDAOImplements implements IAccesosDAO {
	private Gson gson = new Gson();
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public int validadUsuario(InfoUserBean userBean) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean rpta = true;

		int rpta1;
		try {
			tx = session.beginTransaction();
			String e = "select valida_usuario from accesos.valida_usuario( :usuario , :clave )";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("usuario", userBean.getLogin());
			query.setParameter("clave", userBean.getClave());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta1 = ((Integer) ((HashMap) data.get(0)).get("valida_usuario")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg10) {
			if (tx != null) {
				tx.rollback();
			}

			rpta1 = 1;
			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return rpta1;
	}

	public InfoUserBean getUserInfo(String usuario) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		InfoUserBean infoUserBean = new InfoUserBean();

		try {
			tx = session.beginTransaction();
			String e = "select id_usuario,login,clave,estado,fecha_reg,nro_sesion,sesion_activa,nombres,apellidos,foto,genero,dni,correo,id_perfil,perfil,pestado,tiempo_sesion from accesos.info_user( :usuario )";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("usuario", usuario);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			HashMap map = (HashMap) data.get(0);
			infoUserBean.setId_usuario(((Integer) map.get("id_usuario")).intValue());
			infoUserBean.setLogin((String) map.get("login"));
			infoUserBean.setClave((String) map.get("clave"));
			infoUserBean.setEstado(((Integer) map.get("estado")).intValue());
			infoUserBean.setFecha_reg((Date) map.get("fecha_reg"));
			infoUserBean.setNro_sesion(((Integer) map.get("nro_sesion")).intValue());
			infoUserBean.setSesion_activa(((Integer) map.get("sesion_activa")).intValue());
			infoUserBean.setNombres((String) map.get("nombres"));
			infoUserBean.setApellidos((String) map.get("apellidos"));
			infoUserBean.setFoto((String) map.get("foto"));
			infoUserBean.setGenero((String) map.get("genero"));
			infoUserBean.setDni((String) map.get("dni"));
			infoUserBean.setCorreo((String) map.get("correo"));
			infoUserBean.setId_perfil(((Integer) map.get("id_perfil")).intValue());
			infoUserBean.setPerfil((String) map.get("perfil"));
			infoUserBean.setPestado(((Integer) map.get("pestado")).intValue());
			infoUserBean.setTiempo_sesion(((Integer) map.get("tiempo_sesion")).intValue());
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg11) {
			if (tx != null) {
				tx.rollback();
			}

			infoUserBean = null;
			arg11.printStackTrace();
		} finally {
			session.close();
		}

		return infoUserBean;
	}

	public ArrayList<MenuPerfil> get_menus_accesos_perfiles() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		ArrayList lmenus = new ArrayList();

		try {
			tx = session.beginTransaction();
			String e = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,b.icono, b.orden from accesos.permisos a inner join accesos.menu b on b.id_menu=a.id_menu";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			Iterator arg7 = data.iterator();

			while (arg7.hasNext()) {
				Object object = arg7.next();
				Map row = (Map) object;
				MenuPerfil prf = new MenuPerfil();
				prf.setIdPerfil(((Integer) row.get("id_perfil")).intValue());
				prf.setIdMenu(((Integer) row.get("id_menu")).intValue());
				prf.setPadre(((Integer) row.get("padre")).intValue());
				prf.setNivel(((Integer) row.get("nivel")).intValue());
				prf.setNombreMenu((String) row.get("nombre_menu"));
				prf.setUrl((String) row.get("url"));
				prf.setEstado((Integer) row.get("estado"));
				prf.setIcono((String) row.get("icono"));
				prf.setOrden(((Short) row.get("orden")).shortValue());
				lmenus.add(prf);
			}

			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg13) {
			if (tx != null) {
				tx.rollback();
			}

			arg13.printStackTrace();
		} finally {
			session.close();
		}

		return lmenus;
	}

	public ArrayList<MenuPerfil> get_permisos_menus_accesos() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		ArrayList lmenus = new ArrayList();

		try {
			tx = session.beginTransaction();
			String e = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,(select count(m.*)from accesos.menu m where m.padre=b.id_menu)::integer ,b.icono,b.orden from accesos.permisos a right join accesos.menu b on b.id_menu=a.id_menu order by b.orden";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			Iterator arg7 = data.iterator();

			while (arg7.hasNext()) {
				Object object = arg7.next();
				Map row = (Map) object;
				MenuPerfil prf = new MenuPerfil();
				prf.setIdPerfil(((Integer) row.get("id_perfil")).intValue());
				prf.setIdMenu(((Integer) row.get("id_menu")).intValue());
				prf.setPadre(((Integer) row.get("padre")).intValue());
				prf.setNivel(((Integer) row.get("nivel")).intValue());
				prf.setNombreMenu((String) row.get("nombre_menu"));
				prf.setUrl((String) row.get("url"));
				prf.setEstado((Integer) row.get("estado"));
				prf.setNroh(((Integer) row.get("count")).intValue());
				prf.setIcono((String) row.get("icono"));
				prf.setOrden(((Short) row.get("orden")).shortValue());
				lmenus.add(prf);
			}

			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg13) {
			if (tx != null) {
				tx.rollback();
			}

			arg13.printStackTrace();
		} finally {
			session.close();
		}

		return lmenus;
	}

	public ArrayList<TableUsuarioBean> get_list_usuarios() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		ArrayList litsInfoUserBean = new ArrayList();

		try {
			tx = session.beginTransaction();
			String e = "select id_usuario, login, clave, estado, fecha_reg, id_perfil, nro_sesion, sesion_activa, perfil, tiempo_sesion, nombres, apellidos, foto, genero, dni, correo from accesos.vista_usuarios";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			Iterator arg7 = data.iterator();

			while (arg7.hasNext()) {
				Object object = arg7.next();
				Map row = (Map) object;
				TableUsuarioBean infoUserBean = new TableUsuarioBean();
				infoUserBean.setId_usuario(((Integer) row.get("id_usuario")).intValue());
				infoUserBean.setLogin((String) row.get("login"));
				infoUserBean.setClave((String) row.get("clave"));
				infoUserBean.setEstado(((Integer) row.get("estado")).intValue());
				infoUserBean.setFecha_reg((Date) row.get("fecha_reg"));
				infoUserBean.setId_perfil(((Integer) row.get("id_perfil")).intValue());
				infoUserBean.setNro_sesion(((Integer) row.get("nro_sesion")).intValue());
				infoUserBean.setSesion_activa(((Integer) row.get("sesion_activa")).intValue());
				infoUserBean.setPerfil((String) row.get("perfil"));
				infoUserBean.setNombres((String) row.get("nombres"));
				infoUserBean.setApellidos((String) row.get("apellidos"));
				infoUserBean.setFoto((String) row.get("foto"));
				infoUserBean.setGenero((String) row.get("genero"));
				infoUserBean.setDni((String) row.get("dni"));
				infoUserBean.setCorreo((String) row.get("correo"));
				litsInfoUserBean.add(infoUserBean);
			}

			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg13) {
			if (tx != null) {
				tx.rollback();
			}

			litsInfoUserBean = null;
			arg13.printStackTrace();
		} finally {
			session.close();
		}

		return litsInfoUserBean;
	}

	public int registraUsuarios(InfoUserBean usuario) {
		Date fecha = new Date();
		usuario.setFecha_reg(fecha);
		usuario.setEstado(1);
		usuario.setNro_sesion(1);
		usuario.setSesion_activa(1);
		usuario.setFoto("user.png");
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		System.out.println(usuario);
		try {
			tx = session.beginTransaction();
			String e = "select inserta_usuario from accesos.inserta_usuario( :usuario, :clave, :estado, :fecha_reg, :id_perfil, :nro_sesion, :sesion_activa, :nombres, :apellidos, :foto, :genero, :dni, :correo)";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("usuario", usuario.getLogin());
			query.setParameter("clave", usuario.getClave());
			query.setParameter("estado", Integer.valueOf(usuario.getEstado()));
			query.setParameter("fecha_reg", usuario.getFecha_reg());
			query.setParameter("id_perfil", Integer.valueOf(usuario.getId_perfil()));
			query.setParameter("nro_sesion", Integer.valueOf(usuario.getNro_sesion()));
			query.setParameter("sesion_activa", Integer.valueOf(usuario.getSesion_activa()));
			query.setParameter("nombres", "");
			query.setParameter("apellidos","");
			query.setParameter("foto", usuario.getFoto());
			query.setParameter("genero","");
			query.setParameter("dni", "");
			query.setParameter("correo","");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta = ((Integer) ((HashMap) data.get(0)).get("inserta_usuario")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg11) {
			if (tx != null) {
				tx.rollback();
			}

			arg11.printStackTrace();
		} finally {
			session.close();
		}

		return rpta;
	}

	public synchronized ArrayList<PerfilBean> get_list_perfiles() {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		ArrayList litsPerfilBean = new ArrayList();

		try {
			tx = session.beginTransaction();
			String e = "SELECT id_perfil, nombre, estado, fecha, tiempo_sesion FROM accesos.view_perfiles;";
			SQLQuery query = session.createSQLQuery(e);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			Iterator arg7 = data.iterator();

			while (arg7.hasNext()) {
				Object object = arg7.next();
				Map row = (Map) object;
				PerfilBean perfilBean = new PerfilBean();
				perfilBean.setId_perfil(((Integer) row.get("id_perfil")).intValue());
				perfilBean.setNombre((String) row.get("nombre"));
				perfilBean.setEstado(((Integer) row.get("estado")).intValue());
				perfilBean.setFecha((Date) row.get("fecha"));
				perfilBean.setTiempo_sesion(((Integer) row.get("tiempo_sesion")).intValue());
				litsPerfilBean.add(perfilBean);
			}

			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg13) {
			if (tx != null) {
				tx.rollback();
			}

			litsPerfilBean = null;
			arg13.printStackTrace();
		} finally {
			session.close();
		}

		return litsPerfilBean;
	}

	public int registraPerfil(PerfilBean perfil) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();
			String e = "select inserta_perfil from accesos.inserta_perfil( :nombre, :estado, :fecha, :tiempo_sesion);";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("nombre", perfil.getNombre());
			query.setParameter("estado", Integer.valueOf(perfil.getEstado()));
			query.setParameter("fecha", perfil.getFecha());
			query.setParameter("tiempo_sesion", Integer.valueOf(perfil.getTiempo_sesion()));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta = ((Integer) ((HashMap) data.get(0)).get("inserta_perfil")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg10) {
			if (tx != null) {
				tx.rollback();
			}

			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return rpta;
	}

	public PerfilBean get_perfil(int idPerfil) {
		Object tx = null;
		Session session = sessionFactory.openSession();
		PerfilBean perfilBean = new PerfilBean();

		try {
			String e = "SELECT id_perfil, nombre, estado, fecha, tiempo_sesion FROM accesos.perfiles where id_perfil= :id_perfil ;";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("id_perfil", Integer.valueOf(idPerfil));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			HashMap data = (HashMap) query.list().get(0);
			perfilBean.setId_perfil(((Integer) data.get("id_perfil")).intValue());
			perfilBean.setNombre((String) data.get("nombre"));
			perfilBean.setEstado(((Integer) data.get("estado")).intValue());
			perfilBean.setFecha((Date) data.get("fecha"));
			perfilBean.setTiempo_sesion(((Integer) data.get("tiempo_sesion")).intValue());
		} catch (HibernateException arg10) {
			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return perfilBean;
	}

	public int modificarPerfil(PerfilBean perfil) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();
			String e = "select modifica_perfil from accesos.modifica_perfil( :id_perfil, :nombre, :estado, :tiempo_sesion );";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("id_perfil", Integer.valueOf(perfil.getId_perfil()));
			query.setParameter("nombre", perfil.getNombre());
			query.setParameter("estado", Integer.valueOf(perfil.getEstado()));
			query.setParameter("tiempo_sesion", Integer.valueOf(perfil.getTiempo_sesion()));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta = ((Integer) ((HashMap) data.get(0)).get("modifica_perfil")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg10) {
			if (tx != null) {
				tx.rollback();
			}

			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return rpta;
	}

	public int eliminaUsuario(int idUsuario) {
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();
			String e = "select delete_usuario from accesos.delete_usuario( :idUsuario );";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("idUsuario", Integer.valueOf(idUsuario));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta = ((Integer) ((HashMap) data.get(0)).get("delete_usuario")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg10) {
			if (tx != null) {
				tx.rollback();
			}

			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return rpta;
	}

	public int modificarUsuario(InfoUserBean usuario) {
		usuario.setNro_sesion(1);
		usuario.setSesion_activa(1);
		usuario.setFoto("user.png");
		int rpta = -1;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		try {
			tx = session.beginTransaction();
			String e = "select modifica_usuario from accesos.modifica_usuario( :id_usuario, :usuario, :estado, :id_perfil, :nro_sesion, :sesion_activa, :nombres, :apellidos, :foto, :genero, :dni, :correo)";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("id_usuario", Integer.valueOf(usuario.getId_usuario()));
			query.setParameter("usuario", usuario.getLogin());
			query.setParameter("estado", Integer.valueOf(usuario.getEstado()));
			query.setParameter("id_perfil", Integer.valueOf(usuario.getId_perfil()));
			query.setParameter("nro_sesion", Integer.valueOf(usuario.getNro_sesion()));
			query.setParameter("sesion_activa", Integer.valueOf(usuario.getSesion_activa()));
			query.setParameter("nombres", usuario.getNombres());
			query.setParameter("apellidos", usuario.getApellidos());
			query.setParameter("foto", usuario.getFoto());
			query.setParameter("genero", usuario.getGenero());
			query.setParameter("dni", usuario.getDni());
			query.setParameter("correo", usuario.getCorreo());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta = ((Integer) ((HashMap) data.get(0)).get("modifica_usuario")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg10) {
			if (tx != null) {
				tx.rollback();
			}

			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return rpta;
	}

	public InfoUserBean get_usuario(int idUsuario) {
		Object tx = null;
		Session session = sessionFactory.openSession();
		InfoUserBean userBean = new InfoUserBean();

		try {
			String e = "select id_usuario, login, clave, estado, fecha_reg, id_perfil, nro_sesion, sesion_activa, perfil, tiempo_sesion, nombres, apellidos, foto, genero, dni, correo from accesos.vista_usuarios where id_usuario= :id_usuario";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("id_usuario", Integer.valueOf(idUsuario));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			HashMap data = (HashMap) query.list().get(0);
			userBean.setId_usuario(((Integer) data.get("id_usuario")).intValue());
			userBean.setLogin((String) data.get("login"));
			userBean.setClave((String) data.get("clave"));
			userBean.setEstado(((Integer) data.get("estado")).intValue());
			userBean.setFecha_reg((Date) data.get("fecha_reg"));
			userBean.setId_perfil(((Integer) data.get("id_perfil")).intValue());
			userBean.setNro_sesion(((Integer) data.get("nro_sesion")).intValue());
			userBean.setSesion_activa(((Integer) data.get("sesion_activa")).intValue());
			userBean.setPerfil((String) data.get("perfil"));
			userBean.setNombres((String) data.get("nombres"));
			userBean.setApellidos((String) data.get("apellidos"));
			userBean.setFoto((String) data.get("foto"));
			userBean.setGenero((String) data.get("genero"));
			userBean.setDni((String) data.get("dni"));
			userBean.setCorreo((String) data.get("correo"));
		} catch (HibernateException arg10) {
			arg10.printStackTrace();
		} finally {
			session.close();
		}

		return userBean;
	}

	public synchronized ArrayList<MenuPerfil> get_menus_accesos_perfil(int idPerfil) {
		Object tx = null;
		Session session = sessionFactory.openSession();
		ArrayList lmenus = new ArrayList();

		try {
			String e = "select coalesce(a.id_perfil,0) as id_perfil,b.id_menu,b.padre,b.nivel,b.nombre_menu,b.url,b.estado,cast((select count(m.*) from accesos.menu m where m.padre=b.id_menu) as int) as hijos ,b.icono,b.orden \nfrom accesos.permisos a right join accesos.menu b on b.id_menu=a.id_menu and id_perfil= :idPerfil order by b.nivel,b.orden";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("idPerfil", Integer.valueOf(idPerfil));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			Iterator arg8 = data.iterator();

			while (arg8.hasNext()) {
				Object object = arg8.next();
				Map row = (Map) object;
				MenuPerfil prf = new MenuPerfil();
				prf.setIdPerfil(((Integer) row.get("id_perfil")).intValue());
				prf.setIdMenu(((Integer) row.get("id_menu")).intValue());
				prf.setPadre(((Integer) row.get("padre")).intValue());
				prf.setNivel(((Integer) row.get("nivel")).intValue());
				prf.setNombreMenu((String) row.get("nombre_menu"));
				prf.setUrl((String) row.get("url"));
				prf.setEstado((Integer) row.get("estado"));
				prf.setNroh(((Integer) row.get("hijos")).intValue());
				prf.setIcono((String) row.get("icono"));
				prf.setOrden(((Short) row.get("orden")).shortValue());
				lmenus.add(prf);
			}
		} catch (HibernateException arg14) {
			arg14.printStackTrace();
		} finally {
			session.close();
		}

		return lmenus;
	}

	public int cambioPassword(int idUsuario, String passact, String newpass) {
		boolean rpta = true;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		int rpta1;
		try {
			tx = session.beginTransaction();
			String e = "select cambio_password from accesos.cambio_password( :idUsuario , :passact , :newpass )";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("idUsuario", Integer.valueOf(idUsuario));
			query.setParameter("passact", passact);
			query.setParameter("newpass", newpass);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta1 = ((Integer) ((HashMap) data.get(0)).get("cambio_password")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg12) {
			if (tx != null) {
				tx.rollback();
			}

			rpta1 = 1;
			arg12.printStackTrace();
		} finally {
			session.close();
		}

		return rpta1;
	}

	public int asignaPermiso(boolean opcion, int id_perfil, int id_menu) {
		boolean rpta = true;
		Transaction tx = null;
		Session session = sessionFactory.openSession();

		int rpta1;
		try {
			tx = session.beginTransaction();
			String e = "select asigna_permiso from accesos.asigna_permiso( :opcion , :id_perfil, :id_menu)";
			SQLQuery query = session.createSQLQuery(e);
			query.setParameter("opcion", Boolean.valueOf(opcion));
			query.setParameter("id_perfil", Integer.valueOf(id_perfil));
			query.setParameter("id_menu", Integer.valueOf(id_menu));
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data = query.list();
			rpta1 = ((Integer) ((HashMap) data.get(0)).get("asigna_permiso")).intValue();
			if (!tx.wasCommitted()) {
				tx.commit();
			}
		} catch (HibernateException arg12) {
			if (tx != null) {
				tx.rollback();
			}

			rpta1 = 1;
			arg12.printStackTrace();
		} finally {
			session.close();
		}

		return rpta1;
	}
}