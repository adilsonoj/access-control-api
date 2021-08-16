new Vue({
  el: "#main",
  vuetify: new Vuetify(),
  data: {
    modulos: [],
    moduloSelecionado: "",
    cpf: "",
    pessoa: { nmFunc: "" },
    oiSelecionado: "",
    perfis: [],
    perfisAssociados: [],
    listaOi: [],
    listaCompletaOi: [],
    snack: { open: false, text: "", color: "", timeout: 2000 },
  },
  watch: {
    cpf(val) {
      if (val.length < 11) {
        this.pessoa = { nmFunc: "" };
        return;
      }

      this.getPessoaPorCpf(val);
    },
  },
  computed: {
    findOi() {
      return this.listaCompletaOi.find((i) => i.oi === this.oiSelecionado);
    },
  },
  methods: {
    associaPerfil(item) {
      if (!this.oiSelecionado) return;

      const perfil = { ...item, oi: this.oiSelecionado };
      const existe = this.perfisAssociados.find(
        (f) =>
          f.oi.concat(f.key.cdNivlAces) ===
          perfil.oi.concat(perfil.key.cdNivlAces)
      );
      if (existe) {
        return;
      }

      let payload = {
        key: {
          nrCpf01: this.pessoa.nrCpfFunc01,
          nrCpf02: this.pessoa.nrCpfFunc02,
          nrCpf03: this.pessoa.nrCpfFunc03,
          idDvCpf: this.pessoa.idDvCpfFunc,
          cdSupe: perfil.oi[0],
          cdDept: perfil.oi[1],
          cdDivs: perfil.oi[2] ? perfil.oi[2] : " ",
          cdScao: perfil.oi[3] ? perfil.oi[3] : " ",
          cdSetr: perfil.oi[4] ? perfil.oi[4] : " ",
          nivelAcesso: {
            key: {
              cdProj: item.key.cdProj,
              idModu: item.key.idModu,
              cdNivlAces: item.key.cdNivlAces,
            },
          },
        },
      };

      axios
        .post("http://localhost:8082/nivelusuario", payload)
        .then((response) => {
          if (response.status != 201) throw "error";

          this.perfisAssociados.unshift(perfil);
          this.snack = {
            ...this.snack,
            open: true,
            text: "Perfil associado",
            color: "success",
          };
        })
        .catch(
          () =>
            (this.snack = {
              open: true,
              text: "Erro :( ",
              color: "red",
            })
        );
    },
    desassociaPerfil(item) {
      console.log(item);
      let payload = {
        key: {
          nrCpf01: this.pessoa.nrCpfFunc01,
          nrCpf02: this.pessoa.nrCpfFunc02,
          nrCpf03: this.pessoa.nrCpfFunc03,
          idDvCpf: this.pessoa.idDvCpfFunc,
          cdSupe: item.oi[0],
          cdDept: item.oi[1],
          cdDivs: item.oi[2] ? item.oi[2] : " ",
          cdScao: item.oi[3] ? item.oi[3] : " ",
          cdSetr: item.oi[4] ? item.oi[4] : " ",
          nivelAcesso: {
            key: {
              cdProj: item.key.cdProj,
              idModu: item.key.idModu,
              cdNivlAces: item.key.cdNivlAces,
            },
          },
        },
      };
      console.log(payload);
      var config = {
        method: "delete",
        url: "http://localhost:8082/nivelusuario",
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(payload),
      };
      axios(config)
        .then((response) => {
          console.log(response);
          if (response.status != 204) throw "error";
          this.perfisAssociados = this.perfisAssociados.filter((f) => {
            return (
              f.oi.concat(f.key.cdNivlAces) !=
              item.oi.concat(item.key.cdNivlAces)
            );
          });
          this.snack = {
            open: true,
            text: "Perfil removido",
            color: "success",
          };
        })
        .catch(
          () =>
            (this.snack = {
              open: true,
              text: "Erro :( ",
              color: "red",
            })
        );
    },
    handleChangeModulo(item) {
      const cdProj = item.substring(0, 2);
      const idModu = item.substring(2, 4);
      console.log(cdProj, idModu);
      this.getNivelAcesso(cdProj, idModu);
      this.getNivelAcessoPorPessoa(this.cpf, cdProj, idModu);
    },
    handleInputOi: debounce(function (oi) {
      if (!oi) return;
      console.log(oi);
      this.getOi(oi);
    }, 700),

    getModulos() {
      axios
        .get("modulos")
        .then((response) => {
          this.modulos = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getPessoaPorCpf(cpf) {
      axios
        .get(`pessoas/findbycpf?cpf=${cpf}`)
        .then((response) => {
          console.log(response.data);
          this.pessoa = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getNivelAcesso(cdProj, idModu) {
      axios
        .get(`nivelacesso?cdProj=${cdProj}&idModu=${idModu}`)
        .then((response) => {
          console.log(response.data);
          this.perfis = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getNivelAcessoPorPessoa(cpf, cdProj, idModu) {
      axios
        .get(
          `nivelusuario/findByCpfProj?cpf=${cpf}&cdProj=${cdProj}&idModu=${idModu}`
        )
        .then((response) => {
          this.perfisAssociados = response.data.map((e) => {
            return {
              codigoNivelAcesso: e.key.nivelAcesso.codigoNivelAcesso,
              key: e.key.nivelAcesso.key,
              oi: e.oi,
            };
          });
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getOi(oi) {
      axios
        .get(`oi/like?oi=${oi}`)
        .then((response) => {
          console.log(response.data);
          this.listaCompletaOi = response.data;
          this.listaOi = response.data.map((e) => e.oi);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  created() {
    this.getModulos();
    // this.getPessoaPorCpf('05608377710')
    // this.getNivelAcesso('PV', '01')
    // this.getNivelAcessoPorPessoa('05608377710','PV', '01' )
  },
});
