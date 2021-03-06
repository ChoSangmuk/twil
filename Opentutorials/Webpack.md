# Webpack

- [생활코딩 홈페이지](https://opentutorials.org/module/4566)
- [Youtube divide](https://www.youtube.com/playlist?list=PLuHgQVnccGMChcT9IKopFDoAIoTA-03DA)

## 1. 수업소개
- [WEB2 JavaScript](https://opentutorials.org/module/3180) 수업 참고
  - [학습 내용 정리](./JavaScript.md)
- [npm](https://opentutorials.org/module/4044) 수업 참고
  - [학습 내용 정리](./npm.md)

<img src="./Image/Webpack/concept.png" width="750" height="50%"><br>

### [Webpack](https://webpack.kr/) 
- 개요 
  - 브라우저에서 웹 페이지를 동적으로 제어하는 기술인 **JavaScript를 더 잘 다루기 위한 기술**
  - 여러 개의 JS, CSS, 이미지와 같은 Assets을 하나의 묶음(Bundle)으로 만들어 주는 도구
- 등장 배경
  - 웹에 요구사항이 점점 더 다양하고 복잡해짐에 따라 점점 더 많은 파일이 필요, 이로 인해 서버와의 통신이 자주 반복되며 자원이 낭비됨
  - 다양한 JavaScript Package를 사용할 경우, 서로 다른 Package에서 서로 같은 이름의 변수나 함수를 사용할 수 있음. 이는 예상하지 못한 충돌, 의도하지 않은 결과를 발생시킴
  - 이러한 문제를 해결하기 위해 등장한 도구를 **Bundler**라고 함
  - Webpack, Browserify, Parcel 등이 이에 속함

## 2. 웹팩이전의 세계와 모듈의 개념
- JS로 HTML의 화면을 그려주는 간단한 예제 작성, Module의 유무에 따른 차이 비교
- 파일 시스템으로는 실행이 불가능하니 VS Code Live Server 플러그인 이용
- withoutModule
  - [index.html](PracticeSource/Webpack/withoutModule/index.html), [hello.js](PracticeSource/Webpack/withoutModule/src/hello.js), [world.js](PracticeSource/Webpack/withoutModule/src/world.js)
  - 서로 같은 이름의 변수를 사용하기 때문에 의도치 않는 결과가 생성될 가능성이 있음
- withModule
  - [index.html](PracticeSource/Webpack/withModule/index.html), [hello.js](PracticeSource/Webpack/withModule/src/hello.js), [world.js](PracticeSource/Webpack/withModule/src/world.js)
  - 모듈을 사용하고 싶은 script 태그의 type을 Module로 설정
  - 전통적인 script 호출 방법 대신 script 태그 내부에서 **import A from 'scriptPath'** 키워드 사용
  - 호출 대상이 되는 script 파일에서는 **export** 키워드로 외부에서 사용될 변수, 함수를 정의
  - Module은 비교적 최신 기능이기 때문에 브라우저간 호환성 문제가 있으며, Module이 많아질수록 더 많은 자원과 시간을 요구함
- 이러한 요구와 문제를 해결하기위해 **Bundler**라는 도구를 사용

## 3. 웹팩의 도입
- Webpack 도입 시, Refactoring 효과를 기대할 수 있음
> Refactoring : 구동되는 방법은 그대로 유지하며, 내부의 코드를 더 효율적으로 바꾸는 과정
- [앞서 작성한 코드](PracticeSource/Webpack/withModule/index.html)의 기능은 유지, 구형 브라우저에서 사용 가능하며 여러 개의 파일을 하나의 파일(Bundle)로 제공
- Webpack을 사용하기 위해 프로젝트를 Node.js Package로 설정하고 Webpack 설치
```sh
# Shell
npm init -y # Node.js Package로 설정
npm install --save-dev webpack webpack-cli # webpack, webpack-cli 설치
```
- startWebpack 코드 생성 
  - [index.html](PracticeSource/Webpack/startWebpack/index.html), [index.js](PracticeSource/Webpack/startWebpack/src/index.js), [hello.js](PracticeSource/Webpack/startWebpack/src/hello.js), [world.js](PracticeSource/Webpack/startWebpack/src/world.js)
- Webpack Bundling
  - Webpack을 사용하여 Application의 entry(기입, 시작) file인 index.js을 Bundling. 이때, index.js에서 사용하는 하위 파일도 포함됨
  - Bundling 결과를 public 폴더에 [index_bundle.js](PracticeSource/Webpack/startWebpack/public/index_bundle.js)로 저장
```sh
# Shell
npx webpack --mode development --entry ./src/index.js --output-path ./public --output-filename index_bundle.js
```

## 4. 설정파일 도입
- Webpack으로 Bundling 진행 시 사용되는 명령어는 프로젝트가 커질수록 별도의 관리가 필요
- 가독성과 재사용성을 증가시킬 방법으로 설정 파일(webpack.config.js)을 만들고, 해당 파일에 Webpack 실행 명령어를 정리
- [공식 홈페이지 Config 문서](https://webpack.kr/configuration/) 참고
  - __dirname는 현재 파일의 경로를 알려주는 Node.js/path 변수
```js
// webpack.config.js
const path = require('path');

module.exports = {
  mode: "development", // "production" | "development" | "none"
  // Chosen mode tells webpack to use its built-in optimizations accordingly.
  entry: "./src/index.js", // string | object | array
  // defaults to ./src
  // Here the application starts executing
  // and webpack starts bundling
  output: {
    // options related to how webpack emits results
    path: path.resolve(__dirname, "public"), // string (default)
    // the target directory for all output files
    // must be an absolute path (use the Node.js path module)
    filename: "index_bundle.js" // string (default)
    // the filename template for entry chunks
  }
}
```
- 설정 파일을 바탕으로 Webpack Bundling 진행
- config 파일 명으로 default 값(webpack.config.js)을 사용했다면 옵션을 주지 않아도됨
```sh
# Shell
npx webpack --config webpack.config.js
npx webpack 
```

## 5. 모드의 도입
- Webpack은 굉장히 많은 설정을 가지기 때문에 기본적인 설정값을 가짐
- mode 옵션은 기본 설정값 중 하나이며, 기본값은 production로 설정됨
  - none, development, production 값을 가질 수 있음
- 시스템 환경 변수와 조건문을 통해 webpack.config.js 내부에서 설정을 변경하는 것도 가능
- [공식 홈페이지 Mode 문서](https://webpack.kr/configuration/mode/#root) 참고

## 6. 로더의 도입
- 기존의 HTML에서 CSS를 사용하고 싶다면 link Tag를 통해 해당 파일을 불러옴
- CSS도 Webpack에서 제공하는 Loader(style-loader, css-loader)를 통해 Bundling이 가능
- CSS를 Bundling 해주는 Loader를 설치
```sh
# Shell
npm install --save-dev style-loader css-loader
```
- webpack.config.js 수정 
  - rules, test의 정규표현식에 해당하는 자원은 use에 작성된 loader를 **역순으로 통과** 시킴
  - css-loader가 CSS파일을 JavaScript의 형태로 Bundling해서 Webpack으로 가져옴 
```js
// webpack.config.js
const path = require('path');

module.exports = {
  mode: "development",
  entry: "./src/index.js",
  output: {/* */},
  module: {
    rules: [
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      },
    ],
  },
}
```
- Bundling된 CSS를 사용할 때는 entry 파일에서 해당 CSS를 import
  - style-loader가 JavaScript의 형태로 주입된 CSS를 자동으로 head tag 안에 style 태그로 주입해줌
```js
// index.js
import hello_word from './hello';
import world_word from './world';
import css from './style.css'; // CSS import
document.querySelector('#root').innerHTML = hello_word + " " + world_word;
```
- [공식 홈페이지 Asset Management 문서](https://webpack.kr/guides/asset-management/) 참고
- [공식 홈페이지 Loaders 문서](https://webpack.kr/loaders/) 참고

## 7. output 설정
- 각각의 페이지([index.html](PracticeSource/Webpack/startWebpack/index.html), [about.html](PracticeSource/Webpack/startWebpack/about.html))에서 서로 다른 Bundle을 사용하는 경우의 Bundling 설정
- [webpack.config.js](PracticeSource/Webpack/startWebpack/webpack.config.js)의 entry 속성을 객체 형태{}로 변경, entry 파일 경로와 이름을 지정
- entry 속성에 정의된 파일을 전부 Bundling, filename에 \[name\]을 사용하면 entry에 설정한 이름으로 Bundling됨
  - 결과물 : public/index_bundle.js, public/about_bundle.js
- [공식 홈페이지 Output 문서](https://webpack.kr/configuration/output/) 참고

## 8. 플러그인의 도입
- Webpack은 2가지 형태의 확장 기능이 있음
- Loader
  - Module로 결과물(Bundle)을 만들어가는 과정에서 사용
  - 동작 형태가 정해짐
- Plugin
  - 생성된 결과물를 변형하는데 사용
  - 복합적이고 자유롭게 동작
  - Plugin 마다 사용법이 제각각
- [공식 홈페이지 Plugin 문서](https://webpack.kr/plugins/) 참고
  - [HtmlWebpackPlugin](https://webpack.kr/plugins/html-webpack-plugin/) 연습
```sh
# Shell
npm install --save-dev html-webpack-plugin
```
- 앞서 작성한 HTML([index.html](PracticeSource/Webpack/startWebpack/index.html), [about.html](PracticeSource/Webpack/startWebpack/about.html))은 의존성을 가지고 있는 Bundle을 직접 삽입해서 사용
- HTML파일 자동 생성 혹은 템플릿으로 최종 결과물을 만들고 싶다면 HtmlWebpackPlugin 사용
  - HTML파일을 자원으로서 다루게끔 src 폴더로 이동하고 직접 삽입한 Bundle을 삭제([index.html](PracticeSource/Webpack/usingPlugin/src/index.html), [about.html](PracticeSource/Webpack/usingPlugin/src/about.html))
  - [webpack.config.js](PracticeSource/Webpack/usingPlugin/webpack.config.js)에 Plugin load(require), plugins 속성(배열)에 Plugin 객체의 인스턴스(new Plugin())를 삽입
  - HtmlWebpackPlugin의 [생성자 파라미터의 옵션](https://github.com/jantimon/html-webpack-plugin)(template, filename, chunks) 설정 및 실행
  - Bundling 진행 후, public에 완성된 HTML이 생성됨([index.html](PracticeSource/Webpack/usingPlugin/public/index.html), [about.html](PracticeSource/Webpack/usingPlugin/public/about.html))

## 9. 선물
- Assets을 수정할 때 마다 수동으로 Bundling하는 것은 비효율적
- Webpack 실행 옵션 중, --watch 옵션을 사용하면 Assets이 수정될 때 마다 자동으로 Bundling 해줌
```sh
# Shell
$ npx webpack -h
## ...
Options:
  -c, --config <value...>                Provide path to a webpack configuration file e.g. ./webpack.config.js.
  --config-name <value...>               Name of the configuration to use.
  -m, --merge                            Merge two or more configurations using 'webpack-merge'.
  --env <value...>                       Environment passed to the configuration when it is a function.
  --node-env <value>                     Sets process.env.NODE_ENV to the specified value.
  --progress [value]                     Print compilation progress during build.
  -j, --json [value]                     Prints result as JSON or store it in a file.
  -d, --devtool <value>                  Determine src maps to use.
  --no-devtool                           Do not generate src maps.
  --entry <value...>                     The entry point(s) of your application e.g. ./src/main.js.
  --mode <value>                         Defines the mode to pass to webpack.
  --name <value>                         Name of the configuration. Used when loading multiple configurations.
  -o, --output-path <value>              Output location of the file generated by webpack e.g. ./dist/.
  --stats [value]                        It instructs webpack on how to treat the stats e.g. verbose.
  --no-stats                             Disable stats output.
  -t, --target <value...>                Sets the build target e.g. node.
  --no-target                            Negative 'target' option.
  -w, --watch                            Watch for files changes.
  --no-watch                             Do not watch for file changes.
  --watch-options-stdin                  Stop watching when stdin stream has ended.
  --no-watch-options-stdin               Do not stop watching when stdin stream has ended.
## ...
```

## 10. npm 패키지 사용
- 개발자가 생산한 Assets이외에 npm으로 설치한 작고 다양한 Package를 Webpack을 통해 Bundling할 수 있음
- npm Package 설치 후, 사용을 원하는 Assets을 import해서 사용하면 해당 Package도 Bundling 시 포함됨
- Lodash Package를 설치하고 Webpack을 통해 함께 Bundling
  - [index.js](PracticeSource/Webpack/usingPlugin/src/index.js) 참고
```sh
# Shell
npm i --save lodash
```

## 11. 수업을 마치며
- DevServer
  - 개발 시 사용하는 웹 서버 
  - Live Reload 
  - Hot Module Replacement
- Code Splitting
  - 필요에 따라 Bundle을 나눔
- Lazy Loading
  - 나누어진 Bundle을 재구성

## Reference