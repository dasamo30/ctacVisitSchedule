package com.ctac.controller;

import com.ctac.bean.InfoUserBean;
import com.ctac.libraries.Menu_recursivo;
import com.ctac.service.ServiceAccesos;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorLogin {
	private ServiceAccesos serviceAccesos = new ServiceAccesos();
	private static final Logger log = Logger.getLogger(ControladorLogin.class);

	@RequestMapping({"/"})
	public String home() {
		return "panel/home";
	}

	@RequestMapping({"/login"})
	public ModelAndView WiewLogin(HttpServletRequest request, HttpServletResponse res) {
		log.info("ModelAndView WiewLogin");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		String datefooter = ft.format(dNow);
		HttpSession sesionOk = request.getSession();
		ModelAndView mv = null;
		if (sesionOk.getAttribute("usuario") == null) {
			mv = new ModelAndView();
			mv.setViewName("view_login");
			mv.addObject("datefooter", datefooter);
		} else {
			mv = new ModelAndView("redirect:panel/home");
		}

		return mv;
	}
	
	@RequestMapping(value = {"/validatelogin"}, method = {RequestMethod.POST})
	public ModelAndView validatelogin(@ModelAttribute("usuarioBean") InfoUserBean usuarioBean,
			HttpServletRequest request, HttpServletResponse res, RedirectAttributes redir) {
		ModelAndView mv = null;

	
		
		log.info("ModelAndView validatelogin");
		int rpta = this.serviceAccesos.ValidaLogin(usuarioBean);
		if (rpta == 0) {
			HttpSession msjlogin = request.getSession();
			InfoUserBean bean = this.serviceAccesos.getUserInfo(usuarioBean.getLogin());
			msjlogin.setAttribute("usuario", bean.getLogin());
			msjlogin.setMaxInactiveInterval(bean.getTiempo_sesion() * 60);
			String listado = null;
			if (bean.getPestado() == 1) {
				Menu_recursivo file = new Menu_recursivo();
				listado = file.muestra_menu_familias(request.getContextPath(), bean.getId_perfil());
			}
			
			System.out.println(listado.toString());
			log.info(listado.toString());
			msjlogin.setAttribute("lmemus", listado);
			//File file1 = new File("/opt/BroadcasterSms/dist/fotos/user/" + bean.getFoto());
			//File file1 = new File(request.getContextPath()+"/images/user.png");
			String encodedString = null;
			/*
			try {
				FileInputStream fecha = new FileInputStream(file1);
				ByteArrayOutputStream df = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];

				int fecHeader;
				while ((fecHeader = fecha.read(buffer)) != -1) {
					df.write(buffer, 0, fecHeader);
				}

				byte[] fileBytes = df.toByteArray();
				fecha.close();
				df.close();
				byte[] encoded = Base64.encodeBase64(fileBytes);
				encodedString = new String(encoded);
			} catch (FileNotFoundException arg17) {
				log.error(arg17.getMessage(), arg17);
			} catch (IOException arg18) {
				log.error(arg18.getMessage(), arg18);
			}*/

			msjlogin.setAttribute("foto", encodedString);
			msjlogin.setAttribute("nombre", bean.getLogin());
			//msjlogin.setAttribute("nombre", bean.getNombres() + " " + bean.getApellidos());
			msjlogin.setAttribute("perfil", bean.getPerfil());
			msjlogin.setAttribute("pestado", Integer.valueOf(bean.getPestado()));
			msjlogin.setAttribute("idusuario", Integer.valueOf(bean.getId_usuario()));
			new Date();
			DateFormat df1 = DateFormat.getDateInstance(2, new Locale("ES"));
			String fecHeader1 = df1.format(bean.getFecha_reg());
			msjlogin.setAttribute("fechaRegistro", fecHeader1);
			mv = new ModelAndView("redirect:panel/home");
		} else {
			mv = new ModelAndView("redirect:login");
			String msjlogin1;
			switch (rpta) {
				case 1 :
					msjlogin1 = "el usuario no exite";
					break;
				case 2 :
					msjlogin1 = "La ontraseña  es incorrecta";
					break;
				case 3 :
					msjlogin1 = "Usuario inactivo";
					break;
				default :
					msjlogin1 = "Error!!!";
			}

			redir.addFlashAttribute("rpta", msjlogin1);
		}

		return mv;
	}
}