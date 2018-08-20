package com.anson.dyanmic.datasource.test.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 用户登录信息
 * </p>
 *
 * @author simon.pei
 * @since 2018-01-08
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;
	//0:禁止登录
	public static final Integer _0 = new Integer(0);
	//1:有效
	public static final Integer _1 = new Integer(1);



	private Long id;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	@JSONField(serialize=false)
	private String password;
    /**
     * 盐值
     */
    @JSONField(serialize=false)
	private String salt;
	private String email;
    /**
     * 用户头像
     */
	private String pic;
    /**
     * 是否启用
     */
	private Integer checked;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 登录时间
     */
	@TableField("login_time")
	private Date loginTime;
    /**
     * 登录IP
     */
	@TableField("login_ip")
	private String loginIp;

	/**
	 * 用户类型 0管理员1开发者
	 */
	@TableField("user_type")
	private Integer userType;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getUserType() { return userType; }

	public void setUserType(Integer userType) { this.userType = userType; }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", phone=" + phone +
			", username=" + username +
			", password=" + password +
			", salt=" + salt +
			", email=" + email +
			", pic=" + pic +
			", checked=" + checked +
			", createTime=" + createTime +
			", loginTime=" + loginTime +
			", loginIp=" + loginIp +
			"}";
	}
}
