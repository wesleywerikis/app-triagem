package br.com.apptriagem.model;

public class Patient {
    private String name, cpf, phone, address;

    public Patient(String name, String cpf, String phone, String address) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
