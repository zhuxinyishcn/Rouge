/*
 * @Author: Xinyi Zhu
 * @Date: 2020-09-09 19:35:57
 * @Email: zhuxinyishcn@outlook.com
 * @GitHub: https://github.com/zhuxinyishcn
 * @Description: file info
 * @LastEditors: Xinyi Zhu
 * @LastEditTime: 2020-09-09 19:37:32
 */
module.exports = {
  extends: ["@commitlint/config-conventional"],
  husky: {
    hooks: {
      "commit-msg": "commitlint -e $GIT_PARAMS",
    },
  },
};
