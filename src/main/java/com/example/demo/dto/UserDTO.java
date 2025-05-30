package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class UserDTO {
   @NotBlank(message = "Họ tên không được để trống")
   private String hoTen;
   
   @NotBlank(message = "Địa chỉ không được để trống")
   private String diaChi;
   
   @NotBlank(message = "Số điện thoại không được để trống")
   @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại không hợp lệ")
   private String soDienThoai;
   
   private String moTa;
   
   // Constructors, Getters, Setters
   public UserDTO() {}
   
   public String getHoTen() { return hoTen; }
   public void setHoTen(String hoTen) { this.hoTen = hoTen; }
   
   public String getDiaChi() { return diaChi; }
   public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
   
   public String getSoDienThoai() { return soDienThoai; }
   public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
   
   public String getMoTa() { return moTa; }
   public void setMoTa(String moTa) { this.moTa = moTa; }
}