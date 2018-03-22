package br.wiz.view;

import javax.swing.JFrame;
import java.awt.Label;
import java.io.File;
import java.net.URI;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;

import br.wiz.Util;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Home extends JFrame{
	private final JLabel lblm2home = new JLabel("FAIL");
	private final JLabel lbljavahome = new JLabel("FAIL");
	private final JButton btnJavaDirectory = new JButton("...");
	private final JButton btnM2Directory = new JButton("...");
	private final JLabel lblMavenDir = new JLabel("");
	private final JLabel lblJavaDir = new JLabel("");
	private final JButton btnBuild = new JButton("Build Radar");
	
	String dirJava="";
	String dirMaven="";
	Util util = new Util();
	
	public Home() {
		setResizable(false);
		setTitle("Radar Assistant");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Label label = new Label("Environment variables");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setEnabled(false);
		label.setBounds(10, 10, 154, 22);
		getContentPane().add(label);
		
		JLabel lblJavahome = new JLabel("JAVA_HOME");
		lblJavahome.setBounds(10, 62, 81, 14);
		getContentPane().add(lblJavahome);
		
		JLabel lblMhome = new JLabel("M2_HOME");
		lblMhome.setBounds(10, 38, 67, 14);
		getContentPane().add(lblMhome);
		
		
		lblm2home.setForeground(Color.RED);
		lblm2home.setBounds(106, 38, 46, 14);
		getContentPane().add(lblm2home);
		
		
		lbljavahome.setForeground(Color.RED);
		lbljavahome.setBounds(106, 62, 46, 14);
		getContentPane().add(lbljavahome);
		
		btnM2Directory.setBounds(154, 34, 51, 23);
		btnM2Directory.addActionListener(e -> {
            dirMaven = selectFile();
            lblMavenDir.setText(dirMaven);
        });
		getContentPane().add(btnM2Directory);
		
		btnJavaDirectory.setBounds(154, 58, 51, 23);
		btnJavaDirectory.addActionListener(e -> {
            dirJava = selectFile();
            lbljavahome.setText(dirJava);
        });
		getContentPane().add(btnJavaDirectory);
		lblMavenDir.setBounds(230, 38, 254, 14);
		
		getContentPane().add(lblMavenDir);
		lblJavaDir.setBounds(230, 62, 254, 14);
		
		getContentPane().add(lblJavaDir);
		btnBuild.setBounds(10, 130, 89, 23);
		btnBuild.addActionListener(e -> {
            util.build(dirMaven);
        });
		
		getContentPane().add(btnBuild);
		
		verifyStatus();
	}

	private void verifyStatus() {
		
		if(util.existVariable("M2_HOME")) {
			lblm2home.setForeground(Color.GREEN);
			lblm2home.setText("Ok");
			btnM2Directory.setEnabled(false);
		}
		if(util.existVariable("JAVA_HOME")) {
			lbljavahome.setForeground(Color.GREEN);
			lbljavahome.setText("Ok");
			btnJavaDirectory.setEnabled(false);
		}
		
	}
	
	public String selectFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getCurrentDirectory();
            URI uri = f.toURI();
            return uri.toString();
        } else {
            return "";
        }
    }
}
