package com.wkcto.mybatis.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTest01 {

	public static void main(String[] args) {

		SqlSession sqlSession = null;
		try {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsStream("mybatis-config.xml"));
			sqlSession = factory.openSession();
			
			// org.apache.ibatis.session.defaults.DefaultSqlSession@64616ca2
			//System.out.println(sqlSession);
			
			SqlSession sqlSession2 = factory.openSession();
			// org.apache.ibatis.session.defaults.DefaultSqlSession@13fee20c
			System.out.println(sqlSession2);
			
			
			// 查询所有员工姓名
			List<String> enames = sqlSession.selectList("getEnames");
			for(String ename : enames){
				System.out.println(ename);
			}
			
			
			
			// 查询SMITH的“部门名称以及月薪”
			Map<String,String> map = sqlSession.selectOne("getByEname", "SMITH");
			// {ename=SMITH, dname=ACCOUNTING, sal=2100.0}
			System.out.println(map);
			
			
			// 查询出所有工作岗位MANAGER的员工，要求查询员工姓名以及部门名称。
			// List<Map<String,String>> list = sqlSession.selectList("getByJob", "MANAGER");
			// System.out.println(list.toString());
			
			sqlSession.commit();
		} catch (Exception e) {
			if (sqlSession != null) {
				sqlSession.rollback();
			}
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

}
