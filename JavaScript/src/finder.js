const { FT } = require('./ft.js');
const { F } = require('./f.js');

class Finder {
    constructor(p) {
        this._p = p;
    }

    find(ft) {
        let tr = [];

        for (let i = 0; i < this._p.length - 1; i++) {
            for (let j = i + 1; j < this._p.length; j++) {
                let r = new F();
                if (this._p[i].birthDate.getTime() < this._p[j].birthDate.getTime()) {
                    r.P1 = this._p[i];
                    r.P2 = this._p[j];
                } else {
                    r.P1 = this._p[j];
                    r.P2 = this._p[i];
                }
                r.D = r.P2.birthDate.getTime() - r.P1.birthDate.getTime();
                tr.push(r);
            }
        }

        if (tr.length < 1) {
            return new F();
        }

        let answer = tr[0];
        for (let result of tr) {
            switch (ft) {
                case FT.One:
                    if (result.D < answer.D) {
                        answer = result;
                    }
                    break;
                case FT.Two:
                    if (result.D > answer.D) {
                        answer = result;
                    }
                    break;
            }
        }

        return answer;
    }
}

module.exports = {
    Finder
};
