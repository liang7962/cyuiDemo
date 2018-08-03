package com.example.cyui.demo.controller;


import com.example.cyui.demo.dto.Individual.IndividualDto;
import com.example.cyui.demo.dto.login.LoginMessage;
import com.example.cyui.demo.entiy.Individual;
import com.example.cyui.demo.service.LoginService;
import com.example.cyui.demo.util.BeanCopyUtil;
import com.example.cyui.demo.util.RedisUtils;
import com.example.cyui.demo.util.numberRandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@Controller
public class LoginController {

    Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;


    //进入登录页面
    @RequestMapping(value = {"index.do","/"})
    public String toIndex(ModelAndView modelAndView, IndividualDto individualDto){
        modelAndView.addObject("dto",individualDto);
        return "login";
    }


    //执行登录操作
    @RequestMapping("/login.html")
    @ResponseBody
    public LoginMessage getView(ModelAndView modelAndView, IndividualDto individualDto,HttpServletRequest request){
        LoginMessage loginMessage=new LoginMessage();

        HttpSession session=request.getSession(false);
        String sRand= (String) session.getAttribute("randCheckCode");

        if(sRand.equalsIgnoreCase(individualDto.getCaptcha())){
            if (null != individualDto && StringUtils.isNotBlank(individualDto.getUserName()) && StringUtils.isNotBlank(individualDto.getUserPassword())){
                Individual individualIn= BeanCopyUtil.copyTo(individualDto,new Individual());
                Individual individualOut=loginService.selectIndividualByIndividualDto(individualIn);

                //登录成功
                if (null!=individualOut){
                    individualDto=BeanCopyUtil.copyTo(individualOut,new IndividualDto());
                    loginMessage.setCode("0");
                    loginMessage.setMsg("登录成功");
                    String loginToken=individualOut.getId()+":"+numberRandomUtil.getRandomNumber();
                    loginMessage.setToken(loginToken);
                    loginMessage.setIndividual(individualOut);
                    //存入session
                    session.setAttribute("loginMessage",loginMessage);
                    //删除code
                    session.removeAttribute("randCheckCode");
                    //设置session最大会话时间为1h
                    session.setMaxInactiveInterval(3600000);
                    //存入redis
                    try {
                        RedisUtils.set("loginToken"+individualOut.getId(),loginToken);
                    }catch (Exception e){
                        logger.info("Redis开启异常");
                    }
                    return loginMessage;
                }
            }
            loginMessage.setMsg("用户名或密码输入错误，请重新输入");
            return loginMessage;
        }
        loginMessage.setMsg("验证码输入有误，请重新输入");
        return loginMessage;
    }

    /*该方法主要作用是获得随机生成的颜色*/
    public Color getRandColor(int s, int e){
        Random random=new Random();
        if(s>255) s=255;
        if(e>255) e=255;
        int r,g,b;
        r=s+random.nextInt(e-s);    //随机生成RGB颜色中的r值
        g=s+random.nextInt(e-s);    //随机生成RGB颜色中的g值
        b=s+random.nextInt(e-s);    //随机生成RGB颜色中的b值
        return new Color(r,g,b);
    }

    @RequestMapping("/toVerification")
    public void toVerification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("image/jpeg");
        int width=86,height=22;     //指定生成验证码的宽度和高度
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //创建BufferedImage对象,其作用相当于一图片
        Graphics g=image.getGraphics();     //创建Graphics对象,其作用相当于画笔
        Graphics2D g2d=(Graphics2D)g;       //创建Grapchics2D对象
        Random random=new Random();
        Font mfont=new Font("楷体",Font.BOLD,16); //定义字体样式
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);    //绘制背景
        g.setFont(mfont);                   //设置字体
        g.setColor(getRandColor(180,200));

        //绘制100条颜色和位置全部为随机产生的线条,该线条为2f
        for(int i=0;i<100;i++){
            int x=random.nextInt(width-1);
            int y=random.nextInt(height-1);
            int x1=random.nextInt(6)+1;
            int y1=random.nextInt(12)+1;
            BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); //定制线条样式
            Line2D line=new Line2D.Double(x,y,x+x1,y+y1);
            g2d.setStroke(bs);
            g2d.draw(line);     //绘制直线
        }
        //输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。
        String sRand="";
        String ctmp="";
        int itmp=0;
        //制定输出的验证码为四位
        for(int i=0;i<4;i++){
            switch(random.nextInt(3)){
                case 1:     //生成A-Z的字母
                    itmp=random.nextInt(26)+65;
                    ctmp=String.valueOf((char)itmp);
                    break;
                /*case 2:     //生成汉字
                    String[] rBase={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
                    //生成第一位区码
                    int r1=random.nextInt(3)+11;
                    String str_r1=rBase[r1];
                    //生成第二位区码
                    int r2;
                    if(r1==13){
                        r2=random.nextInt(7);
                    }else{
                        r2=random.nextInt(16);
                    }
                    String str_r2=rBase[r2];
                    //生成第一位位码
                    int r3=random.nextInt(6)+10;
                    String str_r3=rBase[r3];
                    //生成第二位位码
                    int r4;
                    if(r3==10){
                        r4=random.nextInt(15)+1;
                    }else if(r3==15){
                        r4=random.nextInt(15);
                    }else{
                        r4=random.nextInt(16);
                    }
                    String str_r4=rBase[r4];
                    //将生成的机内码转换为汉字
                    byte[] bytes=new byte[2];
                    //将生成的区码保存到字节数组的第一个元素中
                    String str_12=str_r1+str_r2;
                    int tempLow=Integer.parseInt(str_12, 16);
                    bytes[0]=(byte) tempLow;
                    //将生成的位码保存到字节数组的第二个元素中
                    String str_34=str_r3+str_r4;
                    int tempHigh=Integer.parseInt(str_34, 16);
                    bytes[1]=(byte)tempHigh;
                    *//**
                     * 汉字显示
                     *//*
                    //              ctmp=new String(bytes);
                    ctmp = new String(bytes,"gb2312");
                    break;*/
                default:
                    itmp=random.nextInt(10)+48;
                    ctmp=String.valueOf((char)itmp);
                    break;
            }
            sRand+=ctmp;
            Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));
            g.setColor(color);
            //将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
            /*将文字旋转制定角度*/
            Graphics2D g2d_word=(Graphics2D)g;
            AffineTransform trans=new AffineTransform();
            trans.rotate((45)*3.14/180,15*i+8,7);
            /*缩放文字*/
            float scaleSize=random.nextFloat()+0.8f;
            if(scaleSize>1f) scaleSize=1f;
            trans.scale(scaleSize, scaleSize);
            g2d_word.setTransform(trans);
            g.drawString(ctmp, 15*i+18, 14);
        }
        HttpSession session=request.getSession(true);
        session.setAttribute("randCheckCode", sRand);
        g.dispose();    //释放g所占用的系统资源
        ImageIO.write(image,"JPEG",response.getOutputStream()); //输出图片
    }



    /*
    * 退出用户登录信息*/
    @RequestMapping("/quit.html")
    public String Quit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        session.invalidate();//情况session信息
/*        Cookie[] cookie=request.getCookies();
        if (cookie.length>0){
            for(Cookie c:cookie){
                if("autoLogin".equals(c.getName())){
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }*/
        return "login";
    }
}