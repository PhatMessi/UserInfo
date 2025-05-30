package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotBlank(message = "Họ tên không được để trống")
   @Size(min = 2, max = 100, message = "Họ tên phải từ 2-100 ký tự")
   @Column(name = "ho_ten")
   private String hoTen;
   
   @NotBlank(message = "Địa chỉ không được để trống")
   @Size(max = 255, message = "Địa chỉ không được quá 255 ký tự")
   @Column(name = "dia_chi")
   private String diaChi;
   
   @NotBlank(message = "Số điện thoại không được để trống")
   @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại không hợp lệ")
   @Column(name = "so_dien_thoai")
   private String soDienThoai;
   
   @Size(max = 500, message = "Mô tả không được quá 500 ký tự")
   @Column(name = "mo_ta")
   private String moTa;
   
   public User() {}
   
   public User(String hoTen, String diaChi, String soDienThoai, String moTa) {
       this.hoTen = hoTen;
       this.diaChi = diaChi;
       this.soDienThoai = soDienThoai;
       this.moTa = moTa;
   }
   
   public Long getId() { return id; }
   public void setId(Long id) { this.id = id; }
   
   public String getHoTen() { return hoTen; }
   public void setHoTen(String hoTen) { this.hoTen = hoTen; }
   
   public String getDiaChi() { return diaChi; }
   public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
   
   public String getSoDienThoai() { return soDienThoai; }
   public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
   
   public String getMoTa() { return moTa; }
   public void setMoTa(String moTa) { this.moTa = moTa; }
}