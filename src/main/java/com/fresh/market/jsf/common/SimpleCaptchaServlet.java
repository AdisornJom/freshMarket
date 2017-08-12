package com.fresh.market.jsf.common;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.BlockGimpyRenderer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.gimpy.FishEyeGimpyRenderer;
import nl.captcha.gimpy.RippleGimpyRenderer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

/**
 * Servlet implementation class for Servlet: SimpleCaptchaServlet
 *
 */
public class SimpleCaptchaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
         * (non-Java-doc)
         * 
         * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public SimpleCaptchaServlet() {
        super();
    }

    /*
         * (non-Java-doc)
         * 
         * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
         *      HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Color> colors = new ArrayList<>();
        colors.add(Color.black);
        colors.add(Color.red);

        List<Font> fonts = new ArrayList<>();
        fonts.add(new Font("Arial", Font.ITALIC, 32));

        Captcha captcha = new Captcha.Builder(120, 40)
                .addText(new NumbersAnswerProducer(4), new DefaultWordRenderer(colors, fonts))
                .addBackground(new GradiatedBackgroundProducer(Color.white, Color.white))
                .addNoise(new StraightLineNoiseProducer())
                .gimp()
                .addBorder()
                .build();

        // display the image produced
        CaptchaServletUtil.writeImage(response, captcha.getImage());

        // save the captcha value on session
        request.getSession().setAttribute(Captcha.NAME, captcha);
//        request.getSession().setAttribute("simpleCaptcha", captcha);
    }

    /*
         * (non-Java-doc)
         * 
         * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest
         *      request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
