import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBook(query) {
  return request({
    url: '/account/book/list',
    method: 'get',
    params: query
  })
}

// 根据userId查询账本名称列表
export function getBookNameListByUserId(userId) {
  return request({
    url: '/account/book/booknamelist/' + userId,
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getBook(bookId) {
  return request({
    url: '/account/book/' + bookId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBook(data) {
  return request({
    url: '/account/book',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBook(data) {
  return request({
    url: '/account/book',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBook(bookId) {
  return request({
    url: '/account/book/' + bookId,
    method: 'delete'
  })
}
