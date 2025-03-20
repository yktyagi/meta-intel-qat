include qat.inc

#Dual BSD and GPLv2 License
LICENSE = "BSD-3-Clause & GPL-2.0-only"
LIC_FILES_CHKSUM = "\
                    file://LICENSE.GPL;md5=751419260aa954499f7abaabaa882bbe \
                    file://LICENSE.BSD;md5=f2e710964f2b777f4dcf2e1ca7e07ecb \
                    "

DEPENDS += "boost udev zlib openssl nasm-native"

SRC_URI = "https://downloadmirror.intel.com/838409/QAT.L.4.27.0-00006.tar.gz;subdir=qat17 \
           file://qat17-qat-fix-for-cross-compilation-issue.patch \
           file://qat17-qat-remove-local-path-from-makefile.patch \
           file://qat17-update-KDIR-for-cross-compilation.patch \
           file://qat17-Added-include-dir-path.patch \
           file://qat17-qat-add-install-target-and-add-folder.patch \
           file://qat17-usdm_drv-convert-mutex_lock-to-mutex_trylock-to-avio.patch \
           file://qat17-remove-the-deprecated-pci-dma-compat.h-API.patch \
           file://qat17-build-fix.patch \
          "

SRC_URI[sha256sum] = "419c0331de5d2c5b2ed79e627909de297a865c7f52f68a5cbc01d17a0ac1f3eb"

S = "${WORKDIR}/qat17"

do_install:append() {
  install -D -m 0644 ${S}/quickassist/lookaside/access_layer/src/qat_direct/src/libadf_user.a ${D}${base_libdir}/libadf.a

  install -m 0644 ${S}/quickassist/qat/fw/qat_d15xx.bin  ${D}${nonarch_base_libdir}/firmware
  install -m 0644 ${S}/quickassist/qat/fw/qat_d15xx_mmp.bin  ${D}${nonarch_base_libdir}/firmware

  # ICE-D LCC
  install -m 0644 ${S}/quickassist/qat/fw/qat_200xx.bin  ${D}${nonarch_base_libdir}/firmware
  install -m 0644 ${S}/quickassist/qat/fw/qat_200xx_mmp.bin  ${D}${nonarch_base_libdir}/firmware

  # ICE-D HCC
  install -m 0644 ${S}/quickassist/qat/fw/qat_c4xxx.bin ${D}${nonarch_base_libdir}/firmware
  install -m 0644 ${S}/quickassist/qat/fw/qat_c4xxx_mmp.bin ${D}${nonarch_base_libdir}/firmware
}

