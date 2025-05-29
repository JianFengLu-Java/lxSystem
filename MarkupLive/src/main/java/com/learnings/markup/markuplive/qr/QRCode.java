package com.learnings.markup.markuplive.qr;

public class QRCode {
    private String qrCodeUrl; // The URL of the QR code image
    private String qrCodeText; // The text encoded in the QR code

    public QRCode(String qrCodeUrl, String qrCodeText) {
        this.qrCodeUrl = qrCodeUrl;
        this.qrCodeText = qrCodeText;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getQrCodeText() {
        return qrCodeText;
    }

    public void setQrCodeText(String qrCodeText) {
        this.qrCodeText = qrCodeText;
    }
}
