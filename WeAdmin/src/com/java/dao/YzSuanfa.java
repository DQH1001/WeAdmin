package com.java.dao;

import java.util.Random;
import java.util.Scanner;

public class YzSuanfa {
	public int[] hanshu(int[] nums,int count) {
		//�������߶�ʮһ���Ȼ���ʮ�ı���
		int[] nums2=nums;
		//�ĺ��������
		int countAfter=0;
		for (int n=0;n<nums2.length;n++) {
			if(nums2[n]%10>=5) {
				nums2[n]=(nums2[n]/10+1)*10;
			}else {
				nums2[n]=(nums2[n]/10)*10;
			}
			countAfter+=nums[n];
		}
		//��ʾһ���Ըĺ��ֵ
		System.out.println("�޸ĺ�");
		for (int j = 0; j < nums2.length; j++) {
			
			System.out.print(nums2[j]+" / ");
		}
		System.out.println(countAfter);
		
		//�ڽ���������ٵ��������·�װһ��
		if(count-countAfter==0) {
			return nums2;
		}else if(count-countAfter>0) {
			int cha=count-countAfter;
			while(cha>0) {
				//�ҳ���Сֵ
				int min=0;
				for (int j = 0; j < nums2.length; j++) {
					if(j==0) {
						min=nums2[j];
					}
					if(min>nums2[j]) {
						min=nums2[j];
					}
				}
				System.out.println("���·�װ����ǰ�������Сֵ��"+min);
				//��һ����Сֵ��������
				for (int j = 0; j < nums2.length&&cha>0; j++) {
					if(nums2[j]==min) {
						if(cha>=10) {
							nums2[j]+=10;
							cha=cha-10;
						}else{
							nums2[j]+=cha;
							cha=0;
						}
					}
				}
			}
			return nums2;
		}else {
			int cha=count-countAfter;
			while(cha<0) {
				//�ҳ����ֵ
				int max=0;
				for (int j = 0; j < nums2.length; j++) {
					if(j==0) {
						max=nums2[j];
					}
					if(max<nums2[j]) {
						max=nums2[j];
					}
				}
				System.out.println("���·�װ����ǰ��������ֵ��"+max);
				//��һ�����ֵ��������
				for (int j = 0; j < nums2.length&&cha<0; j++) {
					if(nums2[j]==max) {
						if(cha<=-10) {
							nums2[j]-=10;
							cha=cha+10;
						}else{
							nums2[j]+=cha;
							cha=0;
						}
					}
				}
			}
			return nums2;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		YzSuanfa yz=new YzSuanfa();
		Random r1=new Random();
		int aa=1;
		Scanner in=new Scanner(System.in);
		
		while(aa==1) {
			int count=0;
			int changdu=r1.nextInt(5)+5;
			int[] nums=new int[changdu];
			System.out.println("ԭ���飺");
			for (int i = 0; i < nums.length; i++) {
				nums[i]=r1.nextInt(100);
				count+=nums[i];
				System.out.print(nums[i]+" / ");
			}
			System.out.println(count);
			
			
			int[] nums2=yz.hanshu(nums,count);
			int count2=0;
			System.out.println("�������飺");
			for (int i = 0; i < nums2.length; i++) {
				System.out.print(nums2[i]+" / ");
				count2+=nums2[i];
			}
			System.out.println(count2);
			System.out.println("�Ƿ������");
			aa=in.nextInt();
		}
	}

}
