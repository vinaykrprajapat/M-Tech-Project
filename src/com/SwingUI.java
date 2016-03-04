package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculate.DownloadsApi;

import java.util.Scanner;


public class SwingUI extends JPanel 
{
	public void run() {
		
		 final JFrame f = new JFrame("M.Tech Dessertation Project Phase 3");
		
		final JTextField search = new JTextField();
		JButton submit = new JButton("Search");
		JButton downloads = new JButton("Rank Downloads");
		JLabel lab = new JLabel("Enter Search term", JLabel.RIGHT);
		search.setColumns(25); 
		lab.setLabelFor(search);
		final JLabel me = new JLabel();
		
		final JLabel downloadlbl1 = new JLabel();
		downloadlbl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		downloadlbl1.setPreferredSize(new Dimension(400,50));
		
		final JLabel downloadlbl2 = new JLabel();
		downloadlbl2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		downloadlbl2.setPreferredSize(new Dimension(400,50));
		
		final JLabel downloadlbl3 = new JLabel();
		downloadlbl3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		downloadlbl3.setPreferredSize(new Dimension(400,50));
		
		final JLabel downloadlbl4 = new JLabel();
		downloadlbl4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		downloadlbl4.setPreferredSize(new Dimension(400,50));
		
		final JLabel downloadlbl5 = new JLabel();
		downloadlbl5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		downloadlbl5.setPreferredSize(new Dimension(400,50));
		
		submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		String s=search.getText();
		Main ob=new Main();
		Integer count = 0;
		try {
			count = ob.search(s);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		me.setText(count.toString());
	//	JOptionPane.showMessageDialog(f, "Occourences of the string "+ s +" are "+ NumberFormat.getNumberInstance(Locale.US).format(count));
		}
		
		});
             
		downloads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e11) {
			String[] values = new String[9];
			DownloadsApi bb=new DownloadsApi();
			try {
				//Object[] e = (Object[]) new Object();
			String kvs = null;
			for (Object e : bb.downloads()) {
			          kvs = kvs + " " +((Map.Entry<String, Integer>) e).getKey() + " "+ ((Map.Entry<String, Integer>) e).getValue();
			      };
			      values = kvs.split(" ");
				System.out.println(values.length);
			    for(int i=0;i<values.length;i++)
			    {
			    	System.out.println(values[i]);
			    }
				
				
			      downloadlbl1.setText(values[1]+ " : "+ values[2]);
			      downloadlbl2.setText(values[3]+ " : "+ values[4]);
			      downloadlbl3.setText(values[5]+ " : "+ values[6]);
			      downloadlbl4.setText(values[7]+ " : "+ values[8]);
			      downloadlbl5.setText(values[9]+ " : "+ values[10]);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		
		
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel(new GridBagLayout());
	    GridBagConstraints c= new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.insets= new Insets(10,10,10,10);
	    p.add(lab,c);

	    c.gridx = 1;
	    c.gridy = 0;
	    c.insets= new Insets(10,10,10,10);
	    p.add(search,c);
	    
	    c.gridx = 1;
	    c.gridy = 1;
	    c.insets= new Insets(10,10,10,10);
	    p.add(me);
	    
	    c.gridx = 1;
	    c.gridy = 2;
	    c.insets= new Insets(10,10,10,10);
	    p.add(submit,c);
	    
	    c.gridx = 2;
	    c.gridy = 2;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloads,c);
	    
	    c.gridx = 0;
	    c.gridy = 3;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloadlbl1,c);

	    c.gridx = 0;
	    c.gridy = 4;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloadlbl2,c);

	    c.gridx = 0;
	    c.gridy = 5;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloadlbl3,c);
	    
	    c.gridx = 0;
	    c.gridy = 6;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloadlbl4,c);
	    
	    c.gridx = 0;
	    c.gridy = 7;
	    c.insets= new Insets(10,10,10,10);
	    p.add(downloadlbl5,c);



	   
	//	p.setLayout(new FlowLayout());
	  
		
	    f.getContentPane().add(p, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);
	}
}
